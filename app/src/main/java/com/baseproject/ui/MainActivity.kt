package com.baseproject.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.baseproject.App
import com.baseproject.R
import com.baseproject.databinding.ActivityMainBinding
import com.baseproject.util.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding(ActivityMainBinding::bind, R.id.container)
    private val viewModel by viewModel { App.component.mainViewModel }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupTransparentStatusBar()
    }

    private fun setupTransparentStatusBar() {
        binding.root.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
    }
}