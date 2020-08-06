package com.alesh.data.source.local

import android.app.Application
import android.content.Context
import com.alesh.data.common.constants.KeySharedPrefsName
import com.alesh.data.common.constants.KeyUserId
import com.alesh.data.util.get
import com.alesh.data.util.put
import com.alesh.data.util.remove
import javax.inject.Inject

class SharedPreferencesDataSource @Inject constructor(private val app: Application) {

    private val sharedPrefs by lazy {
        app.getSharedPreferences(KeySharedPrefsName, Context.MODE_PRIVATE)
    }

    fun setUserId(id: Int) {
        sharedPrefs.put(KeyUserId, id)
    }

    fun getUserId(): Int {
        return sharedPrefs.get(KeyUserId, 1)
    }

    fun deleteUserInfo() {
        sharedPrefs.remove(KeyUserId)
    }
}