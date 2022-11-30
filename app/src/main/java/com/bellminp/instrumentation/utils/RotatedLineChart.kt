package com.bellminp.instrumentation.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.util.AttributeSet
import com.github.mikephil.charting.charts.LineChart

private const val VERTICAL_ROTATION = 270f

class RotatedLineChart @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LineChart(context, attrs, defStyle) {

    override fun onDraw(canvas: Canvas?) {
        canvas?.let {
            it.translate(0f,height.toFloat())
            it.scale(1f,-1f)

        }
        super.onDraw(canvas)
    }
}