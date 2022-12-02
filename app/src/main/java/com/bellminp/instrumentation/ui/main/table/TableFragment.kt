package com.bellminp.instrumentation.ui.main.table

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.SimpleItemAnimator
import com.bellminp.instrumentation.R
import com.bellminp.instrumentation.databinding.FragmentRecordBinding
import com.bellminp.instrumentation.databinding.FragmentTableBinding
import com.bellminp.instrumentation.model.CellData
import com.bellminp.instrumentation.model.GaugesData
import com.bellminp.instrumentation.model.SelectData
import com.bellminp.instrumentation.model.TableData
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
    private val gaugesData: GaugesData?,
    private val selectedData : ((type: Int, text: String, time: Long) -> Unit)
) : BaseFragment<FragmentTableBinding, TableViewModel>(R.layout.fragment_table) {
    override val viewModel by activityViewModels<TableViewModel>()

    private val adapter = TableAdapter()
    private val otherAdapter = OtherAdapter()

    override fun setupBinding() {
        binding.vm = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLayout()
        initListener()
        initAdapter()
        settingTableData(gaugesData)
    }

    private fun initListener(){
        binding.layoutFrom.setOnClickListener {
            selectDateShow(
                null,
                getUnixTime(viewModel.toDay.value?:""),
                getUnixTime(viewModel.fromDay.value?:""),
                0
            )
        }

        binding.layoutTo.setOnClickListener {
            selectDateShow(
                getUnixTime(viewModel.fromDay.value?:""),
                getUnixTime(),
                getUnixTime(viewModel.toDay.value?:""),
                1
            )
        }
    }

    fun settingTableData(data : GaugesData?){
        viewModel.gaugesData.value = data
        data?.let {
            adapter.submitList(it.getTableData())

            if(it.getTableData().isNotEmpty()){
                val size = it.getTableData()[0].list.size

                val otherList = ArrayList<CellData>()
                for(i in 0 until size){
                    if(i == 0){
                        otherList.add(CellData("비고",title = true, type = 2))
                    }else{
                        otherList.add(CellData("", type = 2))
                    }
                }

                otherAdapter.submitList(listOf(TableData(otherList)))
            }

        }
    }


    fun setSelectData(selectData : SelectData){
        with(viewModel){
            fromDay.value = selectData.fromDay
            days.value = selectData.days
            toDay.value = selectData.toDay
            selectSections.value = selectData.selectSections
            selectGauges.value = selectData.selectGauges

            startUnixTime = selectData.startUnixTime
            endUnixTime = selectData.endUnixTime
        }
    }

    private fun initAdapter(){
        binding.recyclerviewTable.adapter = adapter
        val animator = binding.recyclerviewTable.itemAnimator
        if (animator is SimpleItemAnimator) {
            animator.supportsChangeAnimations = false
        }

        binding.recyclerviewOther.adapter = otherAdapter
        val animatorOther = binding.recyclerviewOther.itemAnimator
        if (animatorOther is SimpleItemAnimator) {
            animatorOther.supportsChangeAnimations = false
        }
    }

    private fun initLayout(){
        setSelectData(selectData)
    }

    override fun selectedDate(type: Int, text: String, time: Long) {
        selectedData(type,text,time)
    }

}