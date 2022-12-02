package com.bellminp.instrumentation.ui.main.record

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.SimpleItemAnimator
import com.bellminp.instrumentation.R
import com.bellminp.instrumentation.databinding.FragmentRecordBinding
import com.bellminp.instrumentation.mapper.mapToCellData
import com.bellminp.instrumentation.model.GaugesList
import com.bellminp.instrumentation.model.RecordData
import com.bellminp.instrumentation.model.RecordSelectData
import com.bellminp.instrumentation.model.SelectData
import com.bellminp.instrumentation.ui.base.BaseFragment
import com.bellminp.instrumentation.utils.ONE_DAY
import com.bellminp.instrumentation.utils.convertTimestampToDateTerm
import com.bellminp.instrumentation.utils.convertTimestampToDateText
import com.bellminp.instrumentation.utils.getUnixTime
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecordFragment(
    private val recordSelectData: RecordSelectData,
    private val recordList: List<RecordData>?,
    private val selectedData: ((type: Int, text: String, time: Long) -> Unit),
    private val clickRecord: ((date: SelectData) -> Unit)
) : BaseFragment<FragmentRecordBinding, RecordViewModel>(R.layout.fragment_record) {
    override val viewModel by activityViewModels<RecordViewModel>()

    private val adapter = RecordListAdapter {
        it.cellTableData?.let { data ->
            viewModel.getGaugesName(data)
        }
    }

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

    override fun setupObserver() {
        super.setupObserver()

        with(viewModel) {
            setSelectData.observe(viewLifecycleOwner) {
                clickRecord(it)
            }
        }
    }

    fun setSelectData(recordSelectData: RecordSelectData) {
        with(viewModel) {
            fromDay.value = recordSelectData.fromDay
            toDay.value = recordSelectData.toDay

            startUnixTime = recordSelectData.startUnixTime
            endUnixTime = recordSelectData.endUnixTime
        }
    }

    private fun initAdapter() {
        binding.recyclerviewRecord.adapter = adapter
        val animator = binding.recyclerviewRecord.itemAnimator
        if (animator is SimpleItemAnimator) {
            animator.supportsChangeAnimations = false
        }
    }

    fun settingRecordList(list: List<RecordData>?) {
        viewModel.list.value = list
        list?.let {
            adapter.submitList(it.mapToCellData())
            binding.recyclerviewRecord.scrollToPosition(0)
            binding.scrollView.scrollTo(0, 0)
        }
    }

    private fun initLayout() {
        setSelectData(recordSelectData)
    }

    private fun initListener() {
        binding.layoutFrom.setOnClickListener {
            selectDateShow(
                null,
                getUnixTime(viewModel.toDay.value ?: ""),
                getUnixTime(viewModel.fromDay.value ?: ""),
                0
            )
        }

        binding.layoutTo.setOnClickListener {
            selectDateShow(
                getUnixTime(viewModel.fromDay.value ?: ""),
                getUnixTime(),
                getUnixTime(viewModel.toDay.value ?: ""),
                1
            )
        }
    }

    override fun selectedDate(type: Int, text: String, time: Long) {
        selectedData(type, text, time)
    }
}