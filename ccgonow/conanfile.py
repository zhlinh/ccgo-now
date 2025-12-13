from conan import ConanFile
from conan.tools.cmake import CMake, CMakeToolchain, CMakeDeps, cmake_layout
from conan.tools.files import copy
import os


class CcgonowConan(ConanFile):
    name = "ccgonow"
    version = "1.0.0"
    description = "ccgonow library"
    author = ""
    license = "MIT"
    url = ""

    # Binary configuration
    settings = ['os', 'compiler', 'build_type', 'arch']
    options = {
        "shared": [True, False],
        "fPIC": [True, False],
    }
    default_options = {
        "shared": False,
        "fPIC": True,
    }

    # Sources are located in the same place as this recipe, copy them to the recipe
    exports_sources = "CMakeLists.txt", "src/*", "include/*"

    def config_options(self):
        if self.settings.os == "Windows":
            del self.options.fPIC

    def configure(self):
        if self.options.shared:
            self.options.rm_safe("fPIC")

    def layout(self):
        cmake_layout(self)

    def _get_ccgo_cmake_dir(self):
        """Get CCGO_CMAKE_DIR from ccgo package installation."""
        # First check environment variable
        cmake_dir = os.environ.get("CCGO_CMAKE_DIR")
        if cmake_dir and os.path.isdir(cmake_dir):
            return cmake_dir

        # Try to find from ccgo package installation
        try:
            import ccgo.build_scripts.build_utils as build_utils
            if hasattr(build_utils, "CCGO_CMAKE_DIR") and os.path.isdir(build_utils.CCGO_CMAKE_DIR):
                return build_utils.CCGO_CMAKE_DIR
        except ImportError:
            pass

        # Try to find ccgo package location
        try:
            import ccgo
            ccgo_path = os.path.dirname(ccgo.__file__)
            cmake_dir = os.path.join(ccgo_path, "build_scripts", "cmake")
            if os.path.isdir(cmake_dir):
                return cmake_dir
        except ImportError:
            pass

        raise RuntimeError(
            "CCGO_CMAKE_DIR not found. Please either:\n"
            "1. Install ccgo package: pip install ccgo\n"
            "2. Set CCGO_CMAKE_DIR environment variable"
        )

    def generate(self):
        tc = CMakeToolchain(self)
        # Dynamically get CCGO_CMAKE_DIR from ccgo package installation
        tc.variables["CCGO_CMAKE_DIR"] = self._get_ccgo_cmake_dir()
        # Pass shared option to CMake - CCGO uses CCGO_BUILD_STATIC and CCGO_BUILD_SHARED
        if self.options.shared:
            tc.variables["CCGO_BUILD_STATIC"] = "OFF"
            tc.variables["CCGO_BUILD_SHARED"] = "ON"
        else:
            tc.variables["CCGO_BUILD_STATIC"] = "ON"
            tc.variables["CCGO_BUILD_SHARED"] = "OFF"
        # Also set standard CMake variables for compatibility
        tc.variables["BUILD_SHARED_LIBS"] = "ON" if self.options.shared else "OFF"
        tc.variables["COMM_BUILD_SHARED_LIBS"] = "ON" if self.options.shared else "OFF"
        # Set submodule dependencies for shared library linking
        # Format: "module1,dep1,dep2;module2,dep1" means module1 depends on dep1 and dep2
        tc.variables["CONFIG_COMM_DEPS_MAP"] = self._detect_submodule_deps()
        tc.generate()

        deps = CMakeDeps(self)
        deps.generate()

    def _detect_submodule_deps(self):
        """
        Detect submodule dependencies by scanning source files for includes.

        Returns CMake list format: "module1;dep1,dep2;module2;dep3"
        where even indices are module names and odd indices are comma-separated deps.
        """
        import os
        import re

        src_dir = os.path.join(self.source_folder, "src")
        if not os.path.isdir(src_dir):
            return ""

        # Get list of submodules
        submodules = []
        for item in os.listdir(src_dir):
            subdir = os.path.join(src_dir, item)
            if os.path.isdir(subdir) and not item.startswith('.'):
                submodules.append(item)

        if not submodules:
            return ""

        # Scan each submodule for dependencies on other submodules
        deps_map = []
        include_pattern = re.compile(r'#include\s*[<"]' + self.name + r'/([^/"<>]+)/')

        for module in submodules:
            module_dir = os.path.join(src_dir, module)
            module_deps = set()

            # Scan all source files in this module
            for root, dirs, files in os.walk(module_dir):
                for filename in files:
                    if filename.endswith(('.c', '.cc', '.cpp', '.cxx', '.mm', '.m', '.h', '.hpp')):
                        filepath = os.path.join(root, filename)
                        try:
                            with open(filepath, 'r', encoding='utf-8', errors='ignore') as f:
                                content = f.read()
                                matches = include_pattern.findall(content)
                                for match in matches:
                                    if match != module and match in submodules:
                                        module_deps.add(match)
                        except Exception:
                            pass

            if module_deps:
                # CMake list format: "module;dep1,dep2"
                # Even index = module name, odd index = comma-separated dependencies
                deps_map.append(module)
                deps_map.append(",".join(sorted(module_deps)))

        # Format: "module1;dep1,dep2;module2;dep3"
        result = ";".join(deps_map)
        if result:
            self.output.info(f"Detected submodule dependencies: {result}")
        return result

    def build(self):
        cmake = CMake(self)
        cmake.configure()
        cmake.build()

    def package(self):
        cmake = CMake(self)
        cmake.install()

    def package_info(self):
        self.cpp_info.libs = [self.name]

        # Set the include directories
        self.cpp_info.includedirs = ["include"]

        # Set library directories
        self.cpp_info.libdirs = ["lib"]

        # Set binary directories (for shared libraries on Windows)
        if self.settings.os == "Windows" and self.options.shared:
            self.cpp_info.bindirs = ["bin"]
        else:
            self.cpp_info.bindirs = []
