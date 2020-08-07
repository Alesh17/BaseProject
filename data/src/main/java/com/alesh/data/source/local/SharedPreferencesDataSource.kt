package com.alesh.data.source.local

import android.app.Application
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.alesh.data.common.constants.KeySharedPrefsName
import com.alesh.data.common.constants.KeyUserId
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
            KeySharedPrefsName,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
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