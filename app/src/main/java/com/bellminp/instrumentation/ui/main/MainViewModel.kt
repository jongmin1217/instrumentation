package com.bellminp.instrumentation.ui.main

import androidx.fragment.app.DialogFragment
import com.bellminp.common.timberMsg
import com.bellminp.domain.usecase.LocalUseCase
import com.bellminp.instrumentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val localUseCase: LocalUseCase
): BaseViewModel() {

    init {
        timberMsg("${localUseCase.getAutoLogin()}  ${localUseCase.getToken()}")
    }

    fun clearLocalData(){
        localUseCase.clear()

    }
}