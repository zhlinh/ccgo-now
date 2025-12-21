//
//  Ccgonowdep.h
//  api/apple
//
//  Created by ccgo on 2025-11-30.
//  Copyright 2025 ccgo Project Authors. All rights reserved.

#ifndef CCGONOWDEP_APPLE_H
#define CCGONOWDEP_APPLE_H

#ifdef __cplusplus
extern "C" {
#endif

#include <stdbool.h>

//! \if ZH-CN
//! \file Ccgonowdep.h
//! \brief ccgonowdep C接口文件（用于Kotlin/Native cinterop）
//!
//! \author ccgo
//! \else
//! \file Ccgonowdep.h
//! \brief ccgonowdep C API file (for Kotlin/Native cinterop)
//!
//! \author ccgo
//! \endif

//! \addtogroup iOS
//! \if ZH-CN
//! Ccgonowdep-iOS/macOS平台
//! \else
//! Ccgonowdep for iOS/macOS Platform
//! \endif
//! @{

//-----------------------------------------------------------------------------
//! \if ZH-CN
//!  \name ccgonowdep-基础
//! \else
//!  \name ccgonowdep-base
//! \endif
//!  @{
//-----------------------------------------------------------------------------

//! \if ZH-CN
//! \brief 设置调试日志
//!
//! \param enable 是否启用调试日志（1=启用，0=禁用）
//! \else
//!  \brief Set debug log.
//!
//!  \param enable Enable debug log (1=enable, 0=disable)
//! \endif
void ccgonowdep_set_debug_log(bool enable);

//! \if ZH-CN
//! \brief 获取版本号
//!
//! \return 版本号字符串
//! \else
//!  \brief Get version.
//!
//!  \return Version string
//! \endif
const char* ccgonowdep_get_version(void);

//-----------------------------------------------------------------------------
//!  @}  // end name ccgonowdep-base
//-----------------------------------------------------------------------------

#ifdef __cplusplus
}
#endif

#endif  // CCGONOWDEP_APPLE_H

//!  @}  // end group iOS
