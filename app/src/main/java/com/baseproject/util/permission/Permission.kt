package com.baseproject.util.permission

import android.content.Context
import android.content.pm.PackageManager.PERMISSION_GRANTED
import androidx.core.content.ContextCompat

fun Context.checkPermission(permission: String) =
    ContextCompat.checkSelfPermission(this, permission) == PERMISSION_GRANTED