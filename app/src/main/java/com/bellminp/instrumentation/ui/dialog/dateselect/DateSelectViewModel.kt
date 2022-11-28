package com.bellminp.instrumentation.ui.dialog.dateselect

import androidx.lifecycle.MutableLiveData
import com.bellminp.instrumentation.ui.base.BaseViewModel
import com.bellminp.instrumentation.utils.default

class DateSelectViewModel : BaseViewModel() {

    val year = MutableLiveData<String>().default("")
    val date = MutableLiveData<String>().default("")

    var allDate = ""
}