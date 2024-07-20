package com.example.ghtk_4.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ghtk_4.model.Point
import kotlin.math.abs

class DrawViewModel : ViewModel() {
    private val _fixedPoints = MutableLiveData<List<Pair<Float, Float>>?>(emptyList())
    val fixedPoints: MutableLiveData<List<Pair<Float, Float>>?> = _fixedPoints

    private val _dynamicPoint = MutableLiveData<Pair<Float, Float>?>()
    val dynamicPoint: LiveData<Pair<Float, Float>?> = _dynamicPoint

    fun addPoint(x: Float, y: Float) {
        if (_fixedPoints.value!!.size < 3) {
            _fixedPoints.value = _fixedPoints.value!!.plus(Pair(x, y))
        } else {
            _dynamicPoint.value = Pair(x, y)
        }
    }

    fun clear() {
        _fixedPoints.value = emptyList()
        _dynamicPoint.value = null
    }

    fun isPointInTriangle(px: Float, py: Float): Boolean {
        val points = _fixedPoints.value ?: return false
        if (points.size != 3) return false

        val (x1, y1) = points[0]
        val (x2, y2) = points[1]
        val (x3, y3) = points[2]

        val d1 = sign(px, py, x1, y1, x2, y2)
        val d2 = sign(px, py, x2, y2, x3, y3)
        val d3 = sign(px, py, x3, y3, x1, y1)

        val hasNeg = (d1 < 0) || (d2 < 0) || (d3 < 0)
        val hasPos = (d1 > 0) || (d2 > 0) || (d3 > 0)

        return !(hasNeg && hasPos)
    }

    private fun sign(px: Float, py: Float, x1: Float, y1: Float, x2: Float, y2: Float): Float {
        return (px - x2) * (y1 - y2) - (x1 - x2) * (py - y2)
    }
}