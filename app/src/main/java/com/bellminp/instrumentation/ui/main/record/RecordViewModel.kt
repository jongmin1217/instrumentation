package com.bellminp.instrumentation.ui.main.record

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bellminp.domain.usecase.LocalUseCase
import com.bellminp.instrumentation.model.CellData
import com.bellminp.instrumentation.model.CellTableData
import com.bellminp.instrumentation.model.RecordData
import com.bellminp.instrumentation.model.SelectData
import com.bellminp.instrumentation.ui.base.BaseViewModel
import com.bellminp.instrumentation.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(
    private val localUseCase: LocalUseCase
) : BaseViewModel() {

    private val _setSelectData = SingleLiveEvent<SelectData>()
    val setSelectData: LiveData<SelectData> get() = _setSelectData

    val toDay = MutableLiveData<String>().default("")
    val fromDay = MutableLiveData<String>().default("")

    var startUnixTime = 0L
    var endUnixTime = 0L

    val list = MutableLiveData<List<RecordData>>().default(null)

    fun getGaugesName(cellTableData: CellTableData){
        viewModelScope.launch {
            val nameList = localUseCase.getAllGauges(cellTableData.num).split(" : ")

            try {

                SelectData(
                    convertTimestampToDateText(
                        getUnixTime(
                            convertTimestampToDateTerm(cellTableData.time),
                            false
                        )
                    ),
                    convertTimestampToDateText(
                        getUnixTime(
                            convertTimestampToDateTerm(cellTableData.time - (ONE_DAY * 3)),
                            true
                        )
                    ),
                    "3",
                    nameList[0],
                    nameList[1],
                    getUnixTime(
                        convertTimestampToDateTerm(cellTableData.time - (ONE_DAY * 3)),
                        true
                    ) / 1000,
                    getUnixTime(convertTimestampToDateTerm(cellTableData.time), false) / 1000,
                    cellTableData.num,
                    "gauges"
                ).apply {
                    _setSelectData.value = this
                }

            }catch (e : Exception){}

        }
    }
}