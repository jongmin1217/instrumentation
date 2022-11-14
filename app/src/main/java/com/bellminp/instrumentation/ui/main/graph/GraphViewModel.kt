package com.bellminp.instrumentation.ui.main.graph

import androidx.lifecycle.MutableLiveData
import com.bellminp.instrumentation.ui.base.BaseViewModel
import com.bellminp.instrumentation.utils.default
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GraphViewModel @Inject constructor() : BaseViewModel() {

    val toDay = MutableLiveData<String>().default("")
    val fromDay = MutableLiveData<String>().default("")
    val days = MutableLiveData<String>().default("")
    val selectSections = MutableLiveData<String>().default("")
    val selectGauges = MutableLiveData<String>().default("")
}