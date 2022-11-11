package com.bellminp.instrumentation.ui.main

import androidx.fragment.app.DialogFragment
import androidx.lifecycle.viewModelScope
import com.bellminp.common.timberMsg
import com.bellminp.domain.model.ApiResult
import com.bellminp.domain.usecase.LocalUseCase
import com.bellminp.domain.usecase.RemoteUseCase
import com.bellminp.instrumentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val localUseCase: LocalUseCase,
    private val remoteUseCase: RemoteUseCase
): BaseViewModel() {

    fun getSites(fieldNum : Int){
        viewModelScope.launch {
            remoteUseCase.getSites(localUseCase.getToken(),fieldNum).collect {
                timberMsg(it)
            }
        }
    }

    fun clearLocalData(){
        localUseCase.clear()

    }
}