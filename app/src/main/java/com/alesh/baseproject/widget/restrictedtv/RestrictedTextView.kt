package com.alesh.baseproject.widget.restrictedtv

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.alesh.baseproject.R

class RestrictedTextView(
    context: Context,
    attrs: AttributeSet
) : AppCompatTextView(context, attrs) {

    private var widthPercent: Float = 0f
    private var widthPixels: Float = 0f

    init {
        setupAttributes(attrs)
    }

    private fun setupAttributes(attrs: AttributeSet?) {

        val typedArray = context.theme.obtainStyledAttributes(
            attrs, R.styleable.RestrictedTextView,
            0, 0
        )

        widthPercent =
            typedArray.getFloat(R.styleable.RestrictedTextView_rtv_max_width_percent, 1.0f)
        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        widthPixels = width * widthPercent
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        maxWidth = widthPixels.toInt()
    }
}
