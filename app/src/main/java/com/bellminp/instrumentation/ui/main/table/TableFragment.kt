package com.bellminp.instrumentation.ui.main.table

import androidx.fragment.app.activityViewModels
import com.bellminp.instrumentation.R
import com.bellminp.instrumentation.databinding.FragmentRecordBinding
import com.bellminp.instrumentation.databinding.FragmentTableBinding
import com.bellminp.instrumentation.ui.base.BaseFragment
import com.bellminp.instrumentation.ui.main.record.RecordViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TableFragment : BaseFragment<FragmentTableBinding, TableViewModel>(R.layout.fragment_table) {
    override val viewModel by activityViewModels<TableViewModel>()

    override fun setupBinding() {
        binding.vm = viewModel
    }
}