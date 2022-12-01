package com.bellminp.instrumentation.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.util.AttributeSet
import com.bellminp.common.timberMsg
import com.bellminp.instrumentation.InstrumentationApplication
import com.github.mikephil.charting.charts.LineChart

private const val VERTICAL_ROTATION = -90f

class RotatedLineChart @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LineChart(context, attrs, defStyle) {

    val display = resources.displayMetrics
    val displayWidth = display.widthPixels

    private var _measuredWidth: Int = 0
    private var _measuredHeight: Int = 0
    private val _bounds = Rect()

    override fun onDraw(canvas: Canvas?) {
        canvas?.let {
            timberMsg(it)
            canvas.save()

            canvas.translate(0f, displayWidth.toFloat()*2)
            canvas.rotate(VERTICAL_ROTATION)


        }
        super.onDraw(canvas)
        //setMeasuredDimension(displayWidth, displayWidth*2)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        _measuredHeight = measuredWidth
        _measuredWidth = measuredHeight
        setMeasuredDimension(displayWidth*2, displayWidth*2)
    }
}