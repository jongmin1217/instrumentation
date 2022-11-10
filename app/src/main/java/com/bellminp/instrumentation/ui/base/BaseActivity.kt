package com.bellminp.instrumentation.ui.base

import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.bellminp.common.timberMsg
import com.bellminp.instrumentation.R
import com.bellminp.instrumentation.ui.dialog.fieldselect.FieldSelectDialog
import com.bellminp.instrumentation.ui.login.LoginActivity
import com.bellminp.instrumentation.ui.main.MainActivity
import com.bellminp.instrumentation.utils.TYPE
import com.bellminp.instrumentation.utils.ext.shortToast

abstract class BaseActivity<VDB : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes
    private val layoutResId: Int
) : AppCompatActivity() {

    protected val binding by lazy { DataBindingUtil.setContentView<VDB>(this, layoutResId) }
    protected abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply { lifecycleOwner = this@BaseActivity }

        setupBinding()
        setupObserver()
    }

    abstract fun setupBinding()

    open fun setupObserver(){
        with(viewModel){
            showToast.observe(this@BaseActivity,{ msg->
                showToast(msg)
            })

            goMain.observe(this@BaseActivity,{
                moveMain(it)
            })

            goLogin.observe(this@BaseActivity,{
                Intent(this@BaseActivity, LoginActivity::class.java).apply {
                    startActivity(this)
                    finish()
                }
            })

            showFieldList.observe(this@BaseActivity,{
                FieldSelectDialog(it) { item->
                    moveMain(item.num)
                }.show(supportFragmentManager,"FieldSelectDialog")
            })
        }
    }

    private fun moveMain(fieldNum : Int){
        Intent(this@BaseActivity, MainActivity::class.java).putExtra(TYPE,fieldNum).apply {
            startActivity(this)
            finish()
        }
    }

    fun showToast(msg : String){
        this@BaseActivity.shortToast(msg)
    }

}