package com.bellminp.instrumentation.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.bellminp.common.timberMsg
import com.bellminp.instrumentation.R
import com.bellminp.instrumentation.databinding.ActivityMainBinding
import com.bellminp.instrumentation.model.MenuData
import com.bellminp.instrumentation.ui.base.BaseActivity
import com.bellminp.instrumentation.ui.login.LoginActivity
import com.bellminp.instrumentation.ui.main.graph.GraphFragment
import com.bellminp.instrumentation.ui.main.record.RecordFragment
import com.bellminp.instrumentation.ui.main.table.TableFragment
import com.bellminp.instrumentation.ui.main.tree.TreeFragment
import com.bellminp.instrumentation.ui.splash.SplashViewModel
import com.bellminp.instrumentation.utils.TYPE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    override val viewModel: MainViewModel by viewModels()
    private lateinit var treeFragment :TreeFragment
    private var graphFragment : GraphFragment? = null
    private var tableFragment : TableFragment? = null
    private var recordFragment : RecordFragment? = null

    private val menuAdapter = MenuAdapter {
        when(it.id){
            0 -> changeFragment(treeFragment)

            1 -> {
                if(tableFragment != null) changeFragment(tableFragment!!)
                else {
                    tableFragment = TableFragment()
                    addFragment(tableFragment!!)
                }
            }

            2 -> {
                if(graphFragment != null) changeFragment(graphFragment!!)
                else {
                    graphFragment = GraphFragment()
                    addFragment(graphFragment!!)
                }
            }

            3 -> {
                if(recordFragment != null) changeFragment(recordFragment!!)
                else {
                    recordFragment = RecordFragment()
                    addFragment(recordFragment!!)
                }
            }
        }
    }

    override fun setupBinding() {
        binding.vm = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initLayout(intent.getIntExtra(TYPE, 0))
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

    private fun initLayout(fieldNum : Int){
        treeFragment = TreeFragment(fieldNum)
        supportFragmentManager.beginTransaction().replace(binding.frameLayout.id, treeFragment).commit()
    }


    private fun changeFragment(newFragment: Fragment) {
        if (!newFragment.isVisible) {
            hideFragment()
            supportFragmentManager.beginTransaction().show(newFragment).commit()
        }
    }

    private fun addFragment(newFragment: Fragment) {
        for (fragment: Fragment in supportFragmentManager.fragments) {
            if (fragment.isVisible) {
                supportFragmentManager.beginTransaction().hide(fragment).commit()
                supportFragmentManager.beginTransaction().add(binding.frameLayout.id, newFragment).commit()
            }
        }
    }

    private fun hideFragment() {
        for (fragment: Fragment in supportFragmentManager.fragments) {
            if (fragment.isVisible) {
                supportFragmentManager.beginTransaction().hide(fragment).commit()
            }
        }
    }

}