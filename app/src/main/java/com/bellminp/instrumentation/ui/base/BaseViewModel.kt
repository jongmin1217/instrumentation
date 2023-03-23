package com.bellminp.instrumentation.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bellminp.instrumentation.model.Connect
import com.bellminp.instrumentation.model.FieldList
import com.bellminp.instrumentation.utils.SingleLiveEvent

abstract class BaseViewModel : ViewModel() {

    private val _goMain = SingleLiveEvent<Pair<Int,Connect>>()
    val goMain: LiveData<Pair<Int,Connect>> get() = _goMain

    private val _goLogin = SingleLiveEvent<Unit>()
    val goLogin: LiveData<Unit> get() = _goLogin

    private val _showFieldList = SingleLiveEvent<Pair<List<FieldList>,Connect>>()
    val showFieldList: LiveData<Pair<List<FieldList>,Connect>> get() = _showFieldList

    private val _showToast = SingleLiveEvent<String>()
    val showToast: LiveData<String> get() = _showToast


    fun showFieldList(items : List<FieldList>, connect: Connect){
        _showFieldList.value = items to connect
    }

    fun showShortToast(msg : String){
        _showToast.value = msg
    }

    fun goMain(fieldNum : Int, connect: Connect){
        _goMain.value = fieldNum to connect
    }

    fun goLogin(){
        _goLogin.value = Unit
    }
}