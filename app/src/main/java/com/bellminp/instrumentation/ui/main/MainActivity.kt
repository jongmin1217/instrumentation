package com.bellminp.instrumentation.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.bellminp.common.timberMsg
import com.bellminp.instrumentation.R
import com.bellminp.instrumentation.databinding.ActivityMainBinding
import com.bellminp.instrumentation.ui.base.BaseActivity
import com.bellminp.instrumentation.ui.login.LoginActivity
import com.bellminp.instrumentation.ui.splash.SplashViewModel
import com.bellminp.instrumentation.utils.TYPE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding,MainViewModel>(R.layout.activity_main) {

    override val viewModel: MainViewModel by viewModels()

    override fun setupBinding() {
        binding.vm = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        timberMsg(intent.getIntExtra(TYPE,0))

        initListener()
    }

    private fun initListener(){
        binding.tvTest.setOnClickListener {
            viewModel.clearLocalData()

            Intent(this@MainActivity,LoginActivity::class.java).apply {
                startActivity(this)
                finish()
            }
        }
    }

}