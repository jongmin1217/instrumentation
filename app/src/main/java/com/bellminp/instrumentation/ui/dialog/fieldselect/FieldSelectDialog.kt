package com.bellminp.instrumentation.ui.dialog.fieldselect

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.bellminp.common.timberMsg
import com.bellminp.instrumentation.R
import com.bellminp.instrumentation.databinding.DialogFieldSelectBinding
import com.bellminp.instrumentation.model.FieldList
import com.bellminp.instrumentation.ui.base.BaseDialog
import com.bellminp.instrumentation.ui.base.BaseFragment

class FieldSelectDialog(
    private val items : List<FieldList>,
    private val onItemClick : ((item : FieldList) -> Unit)
) : BaseDialog<DialogFieldSelectBinding,FieldSelectViewModel>(R.layout.dialog_field_select) {
    override val viewModel by activityViewModels<FieldSelectViewModel>()

    private val adapter = FieldListAdapter{
        dismiss()
        it.let(onItemClick)
    }

    override fun setupBinding() {
        binding.vm = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        adapter.submitList(items)
    }


    private fun initAdapter(){
        binding.recyclerviewField.adapter = adapter
    }

}