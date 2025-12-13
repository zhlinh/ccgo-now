//
// Ccgonowdep.java
// wrapper
//
// Created by ccgo on 2025-11-30.
// Copyright 2025 ccgo Project Authors. All rights reserve.

package com.mojeter.ccgo.samples.ccgonowdep.wrapper;

import com.mojeter.ccgo.samples.ccgonowdep.jni.CcgonowdepJni;

/*!
 * \if ZH-CN
 * \file Ccgonowdep.java
 * \brief ccgonowdep通用库java接口文件
 * \else
 * \file Ccgonowdep.java
 * \brief ccgonowdep common library java api file
 * \endif
 */

/*! \addtogroup Android
 * \if ZH-CN
 * Ccgonowdep-Android平台
 * \else
 * Ccgonowdep for Android Platform
 * \endif
 *  @{
 */

/**
 * \if ZH-CN
 * \brief 通用库java接口类
 *
 * @author ccgo
 *         \else
 *         \brief ccgonowdep common java api class.
 *
 * @author ccgo
 *         \endif
 */
public class Ccgonowdep {
    // -----------------------------------------------------------------------------
    // ! \name ccgonowdep-base
    // ! @{
    // -----------------------------------------------------------------------------
    static {
        System.loadLibrary("c++_shared");
    }

    /**
     * \if ZH-CN
     * \brief 设置debug log
     *
     * @param isDebugLog 是否打印日志
     *                   \else
     *                   \brief set debug log.
     *
     * @param isDebugLog the debug log
     *                   \endif
     */
    public static void setDebugLog(boolean isDebugLog) {
        CcgonowdepJni.setDebugLog(isDebugLog);
    }

    // -----------------------------------------------------------------------------
    // endregion
    // ! @} // end name ccgonowdep-base
    // -----------------------------------------------------------------------------
}

/* ! @} */
// end group Android
