package com.baseproject.widget.restrictedtv

import android.content.Context
import android.graphics.Canvas
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.baseproject.R

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
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.RestrictedTextView, 0, 0)
        widthPercent = typedArray.getFloat(R.styleable.RestrictedTextView_rtv_max_width_percent, 1.0f)
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

    /* State Save and Restore */

    override fun onSaveInstanceState(): Parcelable {

        val bundle = Bundle()
        val textToSave = this.text.toString()
        val superState = super.onSaveInstanceState()

        val state = State(textToSave, superState)
        bundle.putParcelable(State.STATE, state)

        return bundle
    }

    override fun onRestoreInstanceState(instanceState: Parcelable?) {
        if (instanceState is Bundle) {
            val state = instanceState.getParcelable(State.STATE) as State? ?: return
            this.text = state.text
            super.onRestoreInstanceState(state.superState)
            return
        }
        super.onRestoreInstanceState(BaseSavedState.EMPTY_STATE)
    }

    private class State(
        val text: String,
        superState: Parcelable?
    ) : BaseSavedState(superState) {

        companion object {
            const val STATE = "RestrictedTextView.State"
        }
    }
}
