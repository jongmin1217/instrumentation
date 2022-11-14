package com.bellminp.instrumentation.ui.main.table

import androidx.lifecycle.MutableLiveData
import com.bellminp.common.timberMsg
import com.bellminp.instrumentation.ui.base.BaseViewModel
import com.bellminp.instrumentation.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TableViewModel @Inject constructor() : BaseViewModel() {

    val toDay = MutableLiveData<String>().default("")
    val fromDay = MutableLiveData<String>().default("")
    val days = MutableLiveData<String>().default("")
    val selectSections = MutableLiveData<String>().default("")
    val selectGauges = MutableLiveData<String>().default("")

}