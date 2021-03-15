package com.baseproject.util

import com.baseproject.BuildConfig

fun getVersionName(): String = BuildConfig.VERSION_NAME

fun getVersionCode(): Int = BuildConfig.VERSION_CODE