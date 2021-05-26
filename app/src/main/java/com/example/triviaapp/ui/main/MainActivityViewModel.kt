package com.example.triviaapp.ui.main

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import com.example.triviaapp.ui.base.BaseViewModel
import com.example.triviaapp.util.toLiveData
import javax.inject.Inject

class MainActivityViewModel @Inject constructor() : BaseViewModel() {
    private val _valideInput = MutableLiveData<String>()
    val valideInput = _valideInput.toLiveData()

    fun CheckInput(name:String) {
        if(TextUtils.isEmpty(name)){
            _valideInput.postValue("0")
        }else{
            _valideInput.postValue("1")
        }
    }
}