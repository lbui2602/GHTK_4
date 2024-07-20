package com.example.ghtk_4.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.abs

class TwoViewModel:ViewModel() {
    private val _result = MutableLiveData<String>()
    val result: LiveData<String> = _result
    fun check(x:Float,y:Float,x1:Float,y1:Float,x2:Float,y2:Float,x3:Float,y3:Float){
        val s:Float=tinhDienTich(x1,y1,x2,y2,x3,y3)
        val s1:Float=tinhDienTich(x,y,x1,y1,x2,y2)
        val s2:Float=tinhDienTich(x,y,x1,y1,x3,y3)
        val s3:Float=tinhDienTich(x,y,x2,y2,x3,y3)
        if(s==(s1+s2+s3)){
            _result.value="Điểm X nằm trong tam giác ABC"
        }else{
            _result.value="Điểm X nằm ngoài tam giác ABC"
        }
    }
    fun tinhDienTich(x1: Float, y1: Float, x2: Float, y2: Float, x3: Float, y3: Float): Float {
        return 0.5f * abs(x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2))
    }
}