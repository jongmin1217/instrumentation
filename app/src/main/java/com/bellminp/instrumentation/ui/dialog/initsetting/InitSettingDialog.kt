package com.bellminp.instrumentation.ui.dialog.initsetting

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.bellminp.instrumentation.InstrumentationApplication
import com.bellminp.instrumentation.R
import com.bellminp.instrumentation.databinding.DialogFieldSelectBinding
import com.bellminp.instrumentation.databinding.DialogInitSettingBinding
import com.bellminp.instrumentation.ui.base.BaseDialog
import com.bellminp.instrumentation.ui.dialog.fieldselect.FieldListAdapter
import com.bellminp.instrumentation.ui.dialog.fieldselect.FieldSelectViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InitSettingDialog(
    private val initClick : (()->Unit)
) : BaseDialog<DialogInitSettingBinding, InitSettingViewModel>(R.layout.dialog_init_setting) {
    override val viewModel by activityViewModels<InitSettingViewModel>()

    override fun setupBinding() {
        binding.vm = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListener()
    }

    private fun initListener(){
        binding.btnCancel.setOnClickListener {
            dismiss()
        }

        binding.btnOk.setOnClickListener {
            viewModel.initSetting()
            with(InstrumentationApplication.mInstance){
                treeSite = true
                treeSection = true
                treeGroup = true
                treeGauges = true
                graphDate = 0
                graphPoint = 0
            }
            dismiss()
            initClick()
        }
    }


}