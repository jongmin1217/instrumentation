package com.bellminp.instrumentation.ui.main.tree

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.bellminp.common.timberMsg
import com.bellminp.domain.model.ApiResult
import com.bellminp.domain.usecase.LocalUseCase
import com.bellminp.domain.usecase.RemoteUseCase
import com.bellminp.instrumentation.model.Field
import com.bellminp.instrumentation.ui.base.BaseViewModel
import com.bellminp.instrumentation.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TreeViewModel @Inject constructor(
    private val localUseCase: LocalUseCase,
    private val remoteUseCase: RemoteUseCase
) : BaseViewModel() {

    private val _addField = SingleLiveEvent<Field>()
    val addField: LiveData<Field> get() = _addField

    fun getSites(fieldNum : Int){
        viewModelScope.launch {
            remoteUseCase.getSites(localUseCase.getToken(), fieldNum).collect {
                when(it.status){
                    ApiResult.Status.SUCCESS -> {
                        it.data?.list?.let { data ->
                            _addField.value = Field(
                                data[0].fieldNum,
                                data[0].fieldName,
                                true
                            )
                        }
                    }
                }
            }
        }
    }
}