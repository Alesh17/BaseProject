package com.alesh.baseproject.util.contract

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.alesh.baseproject.MainActivity

class SampleContract : ActivityResultContract<Int, String?>() {

    override fun createIntent(context: Context, input: Int?): Intent {
        val intent = Intent(context, MainActivity::class.java)
        intent.putExtra("key", input)
        return intent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        return if (resultCode == Activity.RESULT_OK) intent?.getStringExtra("data")
        else null
    }
}