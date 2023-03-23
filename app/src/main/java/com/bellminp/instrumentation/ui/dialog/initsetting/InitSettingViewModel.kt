package com.bellminp.instrumentation.ui.dialog.initsetting

import com.bellminp.domain.usecase.LocalUseCase
import com.bellminp.instrumentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InitSettingViewModel @Inject constructor(
    private val localUseCase: LocalUseCase
) : BaseViewModel() {

    fun initSetting(){
        localUseCase.initSetting()
    }
}