package com.bellminp.instrumentation.ui.main.record

import androidx.lifecycle.MutableLiveData
import com.bellminp.instrumentation.model.RecordData
import com.bellminp.instrumentation.ui.base.BaseViewModel
import com.bellminp.instrumentation.utils.default
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor() : BaseViewModel() {

    val toDay = MutableLiveData<String>().default("")
    val fromDay = MutableLiveData<String>().default("")
    val days = MutableLiveData<String>().default("")

    var startUnixTime = 0L
    var endUnixTime = 0L

    val list = MutableLiveData<List<RecordData>>().default(null)
}