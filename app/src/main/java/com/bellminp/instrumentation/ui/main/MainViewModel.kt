package com.bellminp.instrumentation.ui.main

import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bellminp.common.timberMsg
import com.bellminp.domain.model.ApiResult
import com.bellminp.domain.usecase.LocalUseCase
import com.bellminp.domain.usecase.RemoteUseCase
import com.bellminp.instrumentation.mapper.mapToPresentation
import com.bellminp.instrumentation.mapper.maxText
import com.bellminp.instrumentation.model.RecordData
import com.bellminp.instrumentation.model.SelectData
import com.bellminp.instrumentation.model.SitesList
import com.bellminp.instrumentation.ui.base.BaseViewModel
import com.bellminp.instrumentation.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val localUseCase: LocalUseCase,
    private val remoteUseCase: RemoteUseCase
) : BaseViewModel() {

    private val _setRecordList = SingleLiveEvent<List<RecordData>>()
    val setRecordList: LiveData<List<RecordData>> get() = _setRecordList

    var fieldNum = 0
    val selectData = SelectData()

    fun getProcessLog() {
        viewModelScope.launch {
            remoteUseCase.getProcessLog(
                localUseCase.getToken(),
                if (localUseCase.getAdmin()) null else fieldNum,
                selectData.startUnixTime,
                selectData.endUnixTime
            ).collect {
                if (it.status == ApiResult.Status.SUCCESS) {
                    when (it.data?.code) {
                        0 -> {
                            it.data?.list?.let { list ->
                                val maxData = list.maxText(localUseCase.getAdmin())
                                _setRecordList.value = list.mapToPresentation(localUseCase.getAdmin(),maxData)
                            }
                        }

                        -2 -> {
                            _setRecordList.value = listOf()
                        }
                    }

                }
            }
        }
    }
}