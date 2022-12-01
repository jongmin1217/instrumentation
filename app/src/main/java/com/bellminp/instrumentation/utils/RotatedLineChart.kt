package com.bellminp.instrumentation.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.util.AttributeSet
import com.bellminp.common.timberMsg
import com.github.mikephil.charting.charts.LineChart

private const val VERTICAL_ROTATION = -90f

class RotatedLineChart @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LineChart(context, attrs, defStyle) {

    private var _measuredWidth: Int = 0
    private var _measuredHeight: Int = 0
    private val _bounds = Rect()

    override fun onDraw(canvas: Canvas?) {
        canvas?.let {
            timberMsg(it)
            canvas.save()

            canvas.translate(0f, _measuredHeight.toFloat()*2)
            canvas.rotate(VERTICAL_ROTATION)

            //canvas.restore()
        }
        super.onDraw(canvas)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        timberMsg("$measuredWidth $measuredHeight")
        _measuredHeight = measuredWidth
        _measuredWidth = measuredHeight
        setMeasuredDimension(_measuredWidth, _measuredHeight*2)
    }
}