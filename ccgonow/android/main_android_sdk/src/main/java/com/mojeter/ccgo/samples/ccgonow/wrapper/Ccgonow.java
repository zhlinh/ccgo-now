//
// Ccgonow.java
// wrapper
//
// Created by ccgo on 2025-11-30.
// Copyright 2025 ccgo Project Authors. All rights reserve.

package com.mojeter.ccgo.samples.ccgonow.wrapper;

import com.mojeter.ccgo.samples.ccgonow.jni.CcgonowJni;

/*!
 * \if ZH-CN
 * \file Ccgonow.java
 * \brief ccgonow通用库java接口文件
 * \else
 * \file Ccgonow.java
 * \brief ccgonow common library java api file
 * \endif
 */

/*! \addtogroup Android
 * \if ZH-CN
 * Ccgonow-Android平台
 * \else
 * Ccgonow for Android Platform
 * \endif
 *  @{
 */

/**
 * \if ZH-CN
 * \brief 通用库java接口类
 *
 * @author ccgo
 *         \else
 *         \brief ccgonow common java api class.
 *
 * @author ccgo
 *         \endif
 */
public class Ccgonow {
    // -----------------------------------------------------------------------------
    // ! \name ccgonow-base
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
        CcgonowJni.setDebugLog(isDebugLog);
    }

    // -----------------------------------------------------------------------------
    // endregion
    // ! @} // end name ccgonow-base
    // -----------------------------------------------------------------------------
}

/* ! @} */
// end group Android
