package com.alesh.data.source.local

import android.app.Application
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.alesh.data.common.constants.KEY_SHARED_PREFS_NAME
import com.alesh.data.common.constants.KEY_USER_ID
import com.alesh.data.util.get
import com.alesh.data.util.put
import com.alesh.data.util.remove
import javax.inject.Inject

class SharedPreferencesDataSource @Inject constructor(private val app: Application) {

    private val sharedPrefs by lazy {

        val masterKey = MasterKey.Builder(app)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        EncryptedSharedPreferences.create(
            app,
            KEY_SHARED_PREFS_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun setUserId(id: Int) {
        sharedPrefs.put(KEY_USER_ID, id)
    }

    fun getUserId(): Int {
        return sharedPrefs.get(KEY_USER_ID, 1)
    }

    fun deleteUserInfo() {
        sharedPrefs.remove(KEY_USER_ID)
    }
}