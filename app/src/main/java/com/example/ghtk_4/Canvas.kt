package com.example.ghtk_4

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ghtk_4.ui.MainActivity
import com.example.ghtk_4.viewmodel.DrawViewModel

class Canvas(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val fixedPaint = Paint().apply {
        color = Color.BLACK
        strokeWidth = 5f
    }
    private val dynamicPaint = Paint().apply {
        color = Color.RED
        strokeWidth = 5f
    }
    private val path = android.graphics.Path()

    private val viewModel =
        ViewModelProvider(context as MainActivity).get(DrawViewModel::class.java)

    init {
        viewModel.fixedPoints.observe(context as MainActivity, Observer {
            invalidate()
        })

        viewModel.dynamicPoint.observe(context, Observer {
            invalidate()
        })
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val fixedPoints = viewModel.fixedPoints.value ?: emptyList()
        val dynamicPoint = viewModel.dynamicPoint.value

        for (i in 0 until fixedPoints.size) {
            val point = fixedPoints[i]
            canvas.drawCircle(point.first, point.second, 10f, fixedPaint)
            if (i > 0) {
                val prevPoint = fixedPoints[i - 1]
                canvas.drawLine(
                    prevPoint.first,
                    prevPoint.second,
                    point.first,
                    point.second,
                    fixedPaint
                )
            }
        }

        if (fixedPoints.size == 3) {
            val firstPoint = fixedPoints[0]
            val lastPoint = fixedPoints[2]
            canvas.drawLine(
                firstPoint.first,
                firstPoint.second,
                lastPoint.first,
                lastPoint.second,
                fixedPaint
            )
        }

        dynamicPoint?.let {
            canvas.drawCircle(it.first, it.second, 10f, dynamicPaint)
        }
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            viewModel.addPoint(event.x, event.y)
            if (viewModel.fixedPoints.value?.size == 3 && viewModel.dynamicPoint.value != null) {
                val isInTriangle = viewModel.isPointInTriangle(event.x, event.y)
                Toast.makeText(
                    context,
                    "Điểm nằm ${if (isInTriangle) "trong" else "ngoài"} tam giác",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        return true
    }
    fun clearCanvas() {
        viewModel.clear()
    }
}
