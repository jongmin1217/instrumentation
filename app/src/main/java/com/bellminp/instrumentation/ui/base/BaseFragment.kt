package com.bellminp.instrumentation.ui.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.util.Pair
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.bellminp.common.timberMsg
import com.bellminp.instrumentation.R
import com.bellminp.instrumentation.model.SelectData
import com.bellminp.instrumentation.model.TreeModel
import com.bellminp.instrumentation.ui.dialog.dateselect.DateSelectDialog
import com.bellminp.instrumentation.ui.dialog.fieldselect.FieldSelectDialog
import com.bellminp.instrumentation.ui.login.LoginActivity
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

    open fun setupObserver() {
        with(viewModel) {
            goLogin.observe(viewLifecycleOwner) {
                activity?.let {
                    Intent(it, LoginActivity::class.java).apply {
                        startActivity(this)
                        it.finish()
                    }
                }
            }
        }
    }

    fun selectDateShow(
        minDate: Long?,
        maxDate: Long,
        initDate: Long,
        type: Int
    ) {
        DateSelectDialog(minDate,maxDate,initDate,type) { dateType,text,time->
            selectedDate(dateType, text, time)
        }.show(parentFragmentManager,"DateSelectDialog")
    }

    open fun selectedDate(type : Int, text : String, time : Long){}

}