package com.bellminp.instrumentation.ui.base

import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.bellminp.common.timberMsg
import com.bellminp.instrumentation.R
import com.bellminp.instrumentation.model.Connect
import com.bellminp.instrumentation.model.FieldData
import com.bellminp.instrumentation.model.FieldList
import com.bellminp.instrumentation.ui.dialog.fieldselect.FieldSelectDialog
import com.bellminp.instrumentation.ui.login.LoginActivity
import com.bellminp.instrumentation.ui.main.MainActivity
import com.bellminp.instrumentation.utils.CONNECT_DATA
import com.bellminp.instrumentation.utils.FIELD_DATA
import com.bellminp.instrumentation.utils.NAME
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
            showToast.observe(this@BaseActivity){ msg->
                showToast(msg)
            }

            goMain.observe(this@BaseActivity){
                moveMain(it.first, connect = it.second)
            }

            goLogin.observe(this@BaseActivity){
                Intent(this@BaseActivity, LoginActivity::class.java).apply {
                    startActivity(this)
                    finish()
                }
            }

            showFieldList.observe(this@BaseActivity){
                FieldSelectDialog(it.first) { item->
                    moveMain(item.num,it.first,it.second)
                }.show(supportFragmentManager,"FieldSelectDialog")
            }
        }
    }

    private fun moveMain(fieldNum : Int, field : List<FieldList>? = null,connect: Connect){
        val intent = Intent(this@BaseActivity, MainActivity::class.java)
        intent.putExtra(TYPE,fieldNum)
        field?.let {
            intent.putExtra(FIELD_DATA,FieldData(it))
        }
        intent.putExtra(CONNECT_DATA,connect)
        startActivity(intent)
        finish()
    }

    fun showToast(msg : String){
        this@BaseActivity.shortToast(msg)
    }

}