package com.bellminp.instrumentation.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.bellminp.common.timberMsg
import com.bellminp.instrumentation.R
import com.bellminp.instrumentation.model.SelectData
import com.bellminp.instrumentation.model.TreeModel
import com.bellminp.instrumentation.utils.ONE_DAY
import com.bellminp.instrumentation.utils.convertTimestampToDateTerm
import com.bellminp.instrumentation.utils.convertTimestampToDateText
import com.bellminp.instrumentation.utils.getUnixTime
import com.google.android.material.datepicker.MaterialDatePicker

abstract class BaseFragment<VDB : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes val layoutId: Int
) : Fragment() {
    abstract val viewModel: VM
    abstract fun setupBinding()
    lateinit var binding: VDB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        setupBinding()
        setupObserver()
    }

    open fun setupObserver() {}

    fun showDateSelect() {
        val builder = MaterialDatePicker.Builder.dateRangePicker()
        val picker = builder.build()
        picker.show(parentFragmentManager, picker.toString())

        picker.addOnPositiveButtonClickListener {
            val startPeriod = convertTimestampToDateTerm(it.first ?: 0)
            val endPeriod = convertTimestampToDateTerm(it.second ?: 0)
            val days = ((it.second - it.first) / ONE_DAY).toInt()
            SelectData(
                toDay = convertTimestampToDateText(
                    getUnixTime(
                        endPeriod,
                        false
                    )
                ),
                fromDay = convertTimestampToDateText(
                    getUnixTime(
                        startPeriod,
                        true
                    )
                ),
                days = days.toString(),
                startUnixTime = getUnixTime(
                    startPeriod,
                    true
                ) / 1000,
                endUnixTime = getUnixTime(
                    endPeriod,
                    false
                ) / 1000
            ).apply {
                selectedDate(this)
            }
        }
    }

    open fun selectedDate(selectData : SelectData){}
}