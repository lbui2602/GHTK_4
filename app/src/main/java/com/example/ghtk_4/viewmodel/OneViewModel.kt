package com.example.ghtk_4.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OneViewModel: ViewModel() {
    private val _result = MutableLiveData<String>()
    val result: LiveData<String> = _result
    fun count(input: String) {
        if(input.length==0){
            _result.value="Vui lòng nhập vào ô trống"
        }else{
            _result.value=""
            val count = mutableMapOf<Char, Int>()
            for(char in input) {
                count[char] = count.getOrDefault(char, 0) + 1
            }
            for ((char, count) in count) {
                _result.value+="Kí tự '$char' xuất hiện $count lần"+"\n"
            }
        }
    }
}