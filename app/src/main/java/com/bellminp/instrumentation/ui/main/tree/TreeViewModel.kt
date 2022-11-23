package com.bellminp.instrumentation.ui.main.tree

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.bellminp.common.timberMsg
import com.bellminp.domain.model.ApiResult
import com.bellminp.domain.usecase.LocalUseCase
import com.bellminp.domain.usecase.RemoteUseCase
import com.bellminp.instrumentation.mapper.mapToPresentation
import com.bellminp.instrumentation.model.*
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

    private val _addSites = SingleLiveEvent<List<SitesList>>()
    val addSites: LiveData<List<SitesList>> get() = _addSites

    private val _addSections = SingleLiveEvent<List<SectionsList>>()
    val addSections: LiveData<List<SectionsList>> get() = _addSections

    private val _addGauges = SingleLiveEvent<List<GaugesList>>()
    val addGauges: LiveData<List<GaugesList>> get() = _addGauges

    private val _addGaugesGroup = SingleLiveEvent<List<GaugesGroupList>>()
    val addGaugesGroup: LiveData<List<GaugesGroupList>> get() = _addGaugesGroup

    private val _addSectionsNone = SingleLiveEvent<Int>()
    val addSectionsNone: LiveData<Int> get() = _addSectionsNone

    private val _addGaugesNone = SingleLiveEvent<Int>()
    val addGaugesNone: LiveData<Int> get() = _addGaugesNone

    private val _addGaugesGroupNone = SingleLiveEvent<Int>()
    val addGaugesGroupNone: LiveData<Int> get() = _addGaugesGroupNone

    fun initSearch(fieldNum : Int){
        viewModelScope.launch {
            remoteUseCase.getSites(localUseCase.getToken(), fieldNum).collect {
                if(it.status == ApiResult.Status.SUCCESS){
                    it.data?.list?.let { data ->
                        if(data.isNotEmpty()){
                            _addField.value = Field(
                                data[0].fieldNum,
                                data[0].fieldName,
                                true
                            )
                            _addSites.value = data.mapToPresentation(true)

                            for(i in data.mapToPresentation()){
                                remoteUseCase.getSections(localUseCase.getToken(), i.num).collect {itSection->
                                    if(itSection.status == ApiResult.Status.SUCCESS){
                                        itSection.data?.list?.let { dataSection ->
                                            if(dataSection.isNotEmpty()){
                                                _addSections.value = dataSection.mapToPresentation(true)

                                                for(j in dataSection.mapToPresentation()){
                                                    remoteUseCase.getGauges(localUseCase.getToken(), j.num).collect {itGauges->
                                                        if(itGauges.status == ApiResult.Status.SUCCESS){
                                                            itGauges.data?.list?.let { dataGauges ->
                                                                if(dataGauges.isNotEmpty()) _addGauges.value = dataGauges.mapToPresentation(j.num)
                                                                else _addGaugesNone.value = j.num
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            else _addSectionsNone.value = i.num
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    fun getSites(fieldNum : Int){
        viewModelScope.launch {
            remoteUseCase.getSites(localUseCase.getToken(), fieldNum).collect {
                if(it.status == ApiResult.Status.SUCCESS){
                    it.data?.list?.let { data ->
                        if(data.isNotEmpty()){
                            _addSites.value = data.mapToPresentation()
                        }
                    }
                }
            }
        }
    }

    fun getSections(sitesNum : Int){
        viewModelScope.launch {
            remoteUseCase.getSections(localUseCase.getToken(), sitesNum).collect {
                if(it.status == ApiResult.Status.SUCCESS){
                    it.data?.list?.let { data ->
                        if(data.isNotEmpty()) _addSections.value = data.mapToPresentation()
                        else _addSectionsNone.value = sitesNum
                    }
                }
            }
        }
    }

    fun getGauges(sectionsNum : Int){
        viewModelScope.launch {
            remoteUseCase.getGauges(localUseCase.getToken(), sectionsNum).collect {
                if(it.status == ApiResult.Status.SUCCESS){
                    it.data?.list?.let { data ->
                        if(data.isNotEmpty()) _addGauges.value = data.mapToPresentation(sectionsNum)
                        else _addGaugesNone.value = sectionsNum
                    }
                }
            }
        }
    }

    fun getGaugesGroup(gaugesNum : Int){
        viewModelScope.launch {
            remoteUseCase.getGaugesGroup(localUseCase.getToken(), gaugesNum).collect {
                if(it.status == ApiResult.Status.SUCCESS){
                    it.data?.list?.let { data ->
                        if(data.isNotEmpty()) _addGaugesGroup.value = it.data!!.mapToPresentation()
                        else _addGaugesNone.value = gaugesNum
                    }
                }
            }
        }
    }

    fun logout(){
        localUseCase.clear()
        goLogin()
    }
}