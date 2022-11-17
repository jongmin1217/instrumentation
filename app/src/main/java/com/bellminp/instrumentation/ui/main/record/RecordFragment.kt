package com.bellminp.instrumentation.ui.main.record

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.SimpleItemAnimator
import com.bellminp.instrumentation.R
import com.bellminp.instrumentation.databinding.FragmentRecordBinding
import com.bellminp.instrumentation.mapper.mapToCellData
import com.bellminp.instrumentation.model.RecordData
import com.bellminp.instrumentation.model.SelectData
import com.bellminp.instrumentation.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecordFragment(
    private val selectData : SelectData,
    private val recordList : List<RecordData>?,
    private val selectedData : ((date : SelectData) -> Unit)
) : BaseFragment<FragmentRecordBinding, RecordViewModel>(R.layout.fragment_record) {
    override val viewModel by activityViewModels<RecordViewModel>()

    private val adapter = RecordListAdapter()

    override fun setupBinding() {
        binding.vm = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLayout()
        initListener()
        initAdapter()
        settingRecordList(recordList)
    }

    fun setSelectData(selectData : SelectData){
        with(viewModel){
            fromDay.value = selectData.fromDay
            days.value = selectData.days
            toDay.value = selectData.toDay
        }
    }

    private fun initAdapter(){
        binding.recyclerviewRecord.adapter = adapter
        val animator = binding.recyclerviewRecord.itemAnimator
        if (animator is SimpleItemAnimator) {
            animator.supportsChangeAnimations = false
        }
    }

    fun settingRecordList(list : List<RecordData>?){
        viewModel.list.value = list
        list?.let {
            adapter.submitList(it.mapToCellData())
            binding.recyclerviewRecord.scrollToPosition(0)
            binding.scrollView.scrollTo(0,0)
        }
    }

    private fun initLayout(){
        setSelectData(selectData)
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

    override fun selectedDate(selectData: SelectData) {
        selectedData(selectData)
    }
}