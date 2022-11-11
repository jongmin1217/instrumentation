package com.bellminp.instrumentation.ui.main.graph

import androidx.fragment.app.activityViewModels
import com.bellminp.instrumentation.R
import com.bellminp.instrumentation.databinding.FragmentGraphBinding
import com.bellminp.instrumentation.ui.base.BaseFragment
import com.bellminp.instrumentation.ui.main.tree.TreeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GraphFragment : BaseFragment<FragmentGraphBinding,GraphViewModel>(R.layout.fragment_graph) {
    override val viewModel by activityViewModels<GraphViewModel>()

    override fun setupBinding() {
        binding.vm = viewModel
    }
}