package com.example.ghtk_4.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ghtk_4.model.User
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class ThreeViewModel: ViewModel() {
    private val _user = MutableLiveData<User?>()
    val user: MutableLiveData<User?> get() = _user

    fun loadUserFromAssets(context: Context) {
        viewModelScope.launch {
            val user = withContext(Dispatchers.IO) {
                loadUser(context)
            }
            _user.value = user
        }
    }
    private fun loadUser(context: Context): User? {
        val jsonString: String
        return try {
            jsonString = context.assets.open("user.json").bufferedReader().use { it.readText() }
            Gson().fromJson(jsonString, User::class.java)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            null

        }
    }
}