package com.bellminp.instrumentation.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bellminp.instrumentation.model.FieldList
import com.bellminp.instrumentation.utils.SingleLiveEvent

abstract class BaseViewModel : ViewModel() {

    private val _goMain = SingleLiveEvent<Int>()
    val goMain: LiveData<Int> get() = _goMain

    private val _goLogin = SingleLiveEvent<Unit>()
    val goLogin: LiveData<Unit> get() = _goLogin

    private val _showFieldList = SingleLiveEvent<List<FieldList>>()
    val showFieldList: LiveData<List<FieldList>> get() = _showFieldList

    private val _showToast = SingleLiveEvent<String>()
    val showToast: LiveData<String> get() = _showToast

    fun showFieldList(items : List<FieldList>){
        _showFieldList.value = items
    }

    fun showShortToast(msg : String){
        _showToast.value = msg
    }

    fun goMain(fieldNum : Int){
        _goMain.value = fieldNum
    }

    fun goLogin(){
        _goLogin.value = Unit
    }
}