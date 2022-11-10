package com.bellminp.instrumentation.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.bellminp.instrumentation.R
import com.bellminp.local.prefs.PrefsHelper
import com.bellminp.instrumentation.databinding.ActivityLoginBinding
import com.bellminp.instrumentation.ui.base.BaseActivity
import com.bellminp.instrumentation.ui.main.MainActivity
import com.bellminp.instrumentation.utils.ext.shortToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding,LoginViewModel>(R.layout.activity_login) {

    override val viewModel: LoginViewModel by viewModels()

    override fun setupBinding() {
        binding.vm = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initListener()
    }

    private fun initListener(){
        binding.btnLogin.setOnClickListener {
            with(viewModel){
                if(id.isEmpty() || password.isEmpty()) showToast(resources.getString(R.string.plz_write_login_info))
                else login()
            }
        }
    }

}