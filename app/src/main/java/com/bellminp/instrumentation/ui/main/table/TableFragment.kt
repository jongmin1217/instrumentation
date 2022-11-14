package com.bellminp.instrumentation.ui.main.table

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.bellminp.instrumentation.R
import com.bellminp.instrumentation.databinding.FragmentRecordBinding
import com.bellminp.instrumentation.databinding.FragmentTableBinding
import com.bellminp.instrumentation.model.SelectData
import com.bellminp.instrumentation.ui.base.BaseFragment
import com.bellminp.instrumentation.ui.main.record.RecordViewModel
import com.bellminp.instrumentation.utils.ONE_DAY
import com.bellminp.instrumentation.utils.convertTimestampToDateTerm
import com.bellminp.instrumentation.utils.convertTimestampToDateText
import com.bellminp.instrumentation.utils.getUnixTime
import com.bumptech.glide.util.Util
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TableFragment(
    private val selectData : SelectData,
    private val selectedData : ((date : SelectData) -> Unit)
) : BaseFragment<FragmentTableBinding, TableViewModel>(R.layout.fragment_table) {
    override val viewModel by activityViewModels<TableViewModel>()

    override fun setupBinding() {
        binding.vm = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLayout()
        initListener()
    }

    private fun initListener(){
        binding.layoutFromValue.setOnClickListener {
            showDateSelect()
        }

        binding.layoutDaysValue.setOnClickListener {
            showDateSelect()
        }

        binding.layoutToValue.setOnClickListener {
            showDateSelect()
        }
    }



    fun setSelectData(selectData : SelectData){
        with(viewModel){
            fromDay.value = selectData.fromDay
            days.value = selectData.days
            toDay.value = selectData.toDay
            selectSections.value = selectData.selectSections
            selectGauges.value = selectData.selectGauges
        }
    }

    private fun initLayout(){
        setSelectData(selectData)
    }

    override fun selectedDate(selectData: SelectData) {
        selectedData(selectData)
    }
}