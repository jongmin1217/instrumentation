package com.bellminp.instrumentation.ui.main.record

import androidx.fragment.app.activityViewModels
import com.bellminp.instrumentation.R
import com.bellminp.instrumentation.databinding.FragmentGraphBinding
import com.bellminp.instrumentation.databinding.FragmentRecordBinding
import com.bellminp.instrumentation.ui.base.BaseFragment
import com.bellminp.instrumentation.ui.main.graph.GraphViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecordFragment : BaseFragment<FragmentRecordBinding, RecordViewModel>(R.layout.fragment_record) {
    override val viewModel by activityViewModels<RecordViewModel>()

    override fun setupBinding() {
        binding.vm = viewModel
    }
}