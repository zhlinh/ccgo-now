from conan import ConanFile
from conan.tools.cmake import CMake, CMakeToolchain, CMakeDeps, cmake_layout
from conan.tools.files import copy
import os


class CcgonowdepConan(ConanFile):
    name = "ccgonowdep"
    version = "1.0.1"
    description = "ccgonowdep library"
    author = ""
    license = "MIT"
    url = "git@github.com:zhlinh/ccgonowdep.git"

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

    # Sources are located in parent directory relative to this recipe
    # Conan 2.x doesn't support ".." in exports_sources, so we use export_sources() method
    def export_sources(self):
        # Get the project root directory (parent of conan/ directory)
        project_root = os.path.normpath(os.path.join(self.recipe_folder, ".."))

        # Copy CMakeLists.txt
        copy(self, "CMakeLists.txt", src=project_root, dst=self.export_sources_folder)

        # Copy include directory recursively
        include_src = os.path.join(project_root, "include")
        if os.path.exists(include_src):
            copy(self, "*.h", src=include_src, dst=os.path.join(self.export_sources_folder, "include"), keep_path=True)
            copy(self, "*.hpp", src=include_src, dst=os.path.join(self.export_sources_folder, "include"), keep_path=True)
            copy(self, "*.hxx", src=include_src, dst=os.path.join(self.export_sources_folder, "include"), keep_path=True)

        # Copy src directory recursively
        src_src = os.path.join(project_root, "src")
        if os.path.exists(src_src):
            copy(self, "*.c", src=src_src, dst=os.path.join(self.export_sources_folder, "src"), keep_path=True)
            copy(self, "*.cc", src=src_src, dst=os.path.join(self.export_sources_folder, "src"), keep_path=True)
            copy(self, "*.cpp", src=src_src, dst=os.path.join(self.export_sources_folder, "src"), keep_path=True)
            copy(self, "*.cxx", src=src_src, dst=os.path.join(self.export_sources_folder, "src"), keep_path=True)
            copy(self, "*.h", src=src_src, dst=os.path.join(self.export_sources_folder, "src"), keep_path=True)
            copy(self, "*.hpp", src=src_src, dst=os.path.join(self.export_sources_folder, "src"), keep_path=True)
            copy(self, "*.mm", src=src_src, dst=os.path.join(self.export_sources_folder, "src"), keep_path=True)
            copy(self, "*.m", src=src_src, dst=os.path.join(self.export_sources_folder, "src"), keep_path=True)

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
        tc.variables["CCGO_BUILD_SHARED_LIBS"] = "ON" if self.options.shared else "OFF"
        # Set submodule dependencies for shared library linking
        # Format: "module1,dep1,dep2;module2,dep1" means module1 depends on dep1 and dep2
        tc.variables["CCGO_CONFIG_DEPS_MAP"] = self._detect_submodule_deps()
        tc.generate()

        deps = CMakeDeps(self)
        deps.generate()

    def _detect_submodule_deps(self):
        """
        Get submodule dependencies from CCGO.toml config or auto-detect.

        Returns CMake list format: "module1;dep1,dep2;module2;dep3"
        where even indices are module names and odd indices are comma-separated deps.
        """
        import os
        import re

        # First check if submodule_deps is configured in CCGO.toml
        # Format: {{ "api": ["base"], "feature": ["base", "utils"] }}
        configured_deps = {'api': ['base']}
        if configured_deps:
            deps_map = []
            for module, deps in configured_deps.items():
                # Only process entries where deps is a list (actual dependencies)
                # Skip non-list values like booleans, strings (other build config)
                if isinstance(deps, list) and deps:
                    deps_map.append(module)
                    deps_map.append(",".join(deps))
            result = ";".join(deps_map)
            if result:
                self.output.info(f"Using configured submodule dependencies: {{result}}")
            return result

        # Auto-detect by scanning source files
        # If conanfile.py is in a subdirectory, source_folder points to that subdir
        # We need to look in the parent directory for src/
        src_dir = os.path.join(self.source_folder, "src")
        if not os.path.isdir(src_dir):
            # Try parent directory (conanfile.py might be in conan/ subdirectory)
            src_dir = os.path.join(self.source_folder, "..", "src")
            src_dir = os.path.normpath(src_dir)
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
            self.output.info(f"Auto-detected submodule dependencies: {{result}}")
        return result

    def build(self):
        cmake = CMake(self)
        # Dynamically detect CMakeLists.txt location for conan build vs conan create
        import os
        if not os.path.exists(os.path.join(self.source_folder, "CMakeLists.txt")):
            # conan build scenario: CMakeLists.txt is in parent directory
            cmake.configure(build_script_folder="..")
        else:
            # conan create scenario: CMakeLists.txt is in source folder
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
