package com.bellminp.instrumentation.utils

import androidx.lifecycle.MutableLiveData

@SuppressWarnings("WeakerAccess")
class DelegateLiveData<T>(
    private val onChange: (T,T) -> Unit
) : MutableLiveData<T>() {

    override fun setValue(value: T) {
        val beforeValue = getValue()
        super.setValue(value)
        beforeValue?.let {
            onChange(it,value)
        }
    }
}