package com.bellminp.instrumentation.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.bellminp.common.timberMsg
import com.bellminp.instrumentation.R
import com.bellminp.instrumentation.databinding.ActivityMainBinding
import com.bellminp.instrumentation.model.MenuData
import com.bellminp.instrumentation.ui.base.BaseActivity
import com.bellminp.instrumentation.ui.login.LoginActivity
import com.bellminp.instrumentation.ui.splash.SplashViewModel
import com.bellminp.instrumentation.utils.TYPE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    override val viewModel: MainViewModel by viewModels()

    private val menuAdapter = MenuAdapter {

    }

    override fun setupBinding() {
        binding.vm = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getSites(intent.getIntExtra(TYPE, 0))

        initListener()
        initAdapter()
    }

    private fun initAdapter() {
        binding.recyclerviewMenu.adapter = menuAdapter
        menuAdapter.submitList(
            listOf(
                MenuData(0, "트리", R.drawable.ic_launcher_background, true),
                MenuData(1, "표", R.drawable.ic_launcher_background, false),
                MenuData(2, "그래프", R.drawable.ic_launcher_background, false),
                MenuData(3, "검사기록", R.drawable.ic_launcher_background, false)
            )
        )
    }

    private fun initListener() {
        binding.tvTest.setOnClickListener {
            viewModel.clearLocalData()

            Intent(this@MainActivity, LoginActivity::class.java).apply {
                startActivity(this)
                finish()
            }
        }
    }

}