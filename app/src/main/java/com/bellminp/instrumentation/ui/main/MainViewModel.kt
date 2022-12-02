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
import com.bellminp.instrumentation.model.*
import com.bellminp.instrumentation.ui.base.BaseViewModel
import com.bellminp.instrumentation.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val localUseCase: LocalUseCase,
    private val remoteUseCase: RemoteUseCase
) : BaseViewModel() {

    private val _setRecordList = SingleLiveEvent<List<RecordData>?>()
    val setRecordList: LiveData<List<RecordData>?> get() = _setRecordList

    private val _setGaugesData = SingleLiveEvent<GaugesData?>()
    val setGaugesData: LiveData<GaugesData?> get() = _setGaugesData

    var fieldNum = 0
    var selectData = SelectData()
    var recordSelectData = RecordSelectData()

    fun getProcessLog() {
        viewModelScope.launch {
            remoteUseCase.getProcessLog(
                localUseCase.getToken(),
                fieldNum,
                recordSelectData.startUnixTime,
                recordSelectData.endUnixTime
            ).collect {
                if (it.status == ApiResult.Status.SUCCESS) {
                    when (it.data?.code) {
                        0 -> {
                            it.data?.list?.let { list ->
                                if(recordSelectData.startUnixTime == 0L){
                                    val maxTime = Collections.max(list.map { data -> data.time })
                                    recordSelectData.fromDay = convertTimestampToDateText(
                                        getUnixTime(
                                            convertTimestampToDateTerm(maxTime - (ONE_DAY * 3)),
                                            false
                                        )
                                    )
                                    recordSelectData.toDay = convertTimestampToDateText(
                                        getUnixTime(
                                            convertTimestampToDateTerm(maxTime),
                                            true
                                        )
                                    )
                                    recordSelectData.startUnixTime = getUnixTime(
                                        convertTimestampToDateTerm(maxTime - (ONE_DAY * 3)),
                                        true
                                    )/1000
                                    recordSelectData.endUnixTime = getUnixTime(
                                        convertTimestampToDateTerm(maxTime),
                                        false
                                    )/1000

                                    getProcessLog()
                                }
                                else _setRecordList.value = list.mapToPresentation(localUseCase.getAdmin())
                            }
                        }

                        -2 -> {
                            _setRecordList.value = null
                        }
                    }

                }
            }
        }
    }

    fun getGaugesData(){
        when(selectData.type){
            "group" -> getGaugesGroupDetail()
            "gauges" -> getGaugesDetail()
            else -> _setGaugesData.value = null
        }
    }

    private fun getGaugesDetail(){
        viewModelScope.launch {
            remoteUseCase.getGaugesDetail(
                localUseCase.getToken(),
                selectData.gaugesNum,
                selectData.startUnixTime,
                selectData.endUnixTime
            ).collect {
                if (it.status == ApiResult.Status.SUCCESS){
                    when (it.data?.code){
                        0 -> {
                            it.data?.let{ data ->
                                _setGaugesData.value = data.mapToPresentation()
                            }
                        }

                        -2 ->{
                            _setGaugesData.value = null
                        }

                        else -> {
                            _setGaugesData.value = null
                            showShortToast(it.data?.message?:"")
                        }
                    }
                }else{
                    _setGaugesData.value = null
                    showShortToast("서버 오류")
                }
            }
        }
    }

    private fun getGaugesGroupDetail(){
        viewModelScope.launch {
            remoteUseCase.getGaugesGroupDetail(
                localUseCase.getToken(),
                selectData.gaugesNum,
                selectData.startUnixTime,
                selectData.endUnixTime
            ).collect {

                if (it.status == ApiResult.Status.SUCCESS){

                    when (it.data?.code){
                        0 -> {
                            it.data?.let{ data ->
                                _setGaugesData.value = data.mapToPresentation()
                            }
                        }

                        -2 ->{
                            _setGaugesData.value = null
                        }

                        else -> {
                            _setGaugesData.value = null
                            showShortToast(it.data?.message?:"")
                        }
                    }
                }else{
                    _setGaugesData.value = null
                    showShortToast("서버 오류")
                }
            }
        }
    }
}