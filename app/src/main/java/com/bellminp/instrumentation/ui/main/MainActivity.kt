package com.bellminp.instrumentation.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.bellminp.common.timberMsg
import com.bellminp.instrumentation.R
import com.bellminp.instrumentation.databinding.ActivityMainBinding
import com.bellminp.instrumentation.model.GaugesData
import com.bellminp.instrumentation.model.MenuData
import com.bellminp.instrumentation.model.RecordData
import com.bellminp.instrumentation.model.SelectData
import com.bellminp.instrumentation.ui.base.BaseActivity
import com.bellminp.instrumentation.ui.login.LoginActivity
import com.bellminp.instrumentation.ui.main.graph.GraphFragment
import com.bellminp.instrumentation.ui.main.record.RecordFragment
import com.bellminp.instrumentation.ui.main.table.TableFragment
import com.bellminp.instrumentation.ui.main.tree.TreeFragment
import com.bellminp.instrumentation.ui.splash.SplashViewModel
import com.bellminp.instrumentation.utils.TYPE
import dagger.hilt.android.AndroidEntryPoint
import kotlin.system.exitProcess

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    override val viewModel: MainViewModel by viewModels()
    private lateinit var treeFragment: TreeFragment
    private var graphFragment: GraphFragment? = null
    private var tableFragment: TableFragment? = null
    private var recordFragment: RecordFragment? = null

    private var recordList: List<RecordData>? = null
    private var gaugesData: GaugesData? = null

    private var time: Long = 0

    private val menuAdapter = MenuAdapter {
        when (it.id) {
            0 -> goTree()
            1 -> goTable()
            2 -> goGraph()
            3 -> goRecord()
        }
    }

    override fun setupBinding() {
        binding.vm = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val num = intent.getIntExtra(TYPE, 0)
        initLayout(num)
        initAdapter()


        with(viewModel) {
            fieldNum = num
            getProcessLog()
        }
    }

    private fun goTree(){
        changeFragment(treeFragment)
    }

    private fun goTable(){
        if (tableFragment != null) changeFragment(tableFragment!!)
        else {
            tableFragment = TableFragment(
                viewModel.selectData,
                gaugesData
            ) { type, text, time -> editDateSelectData(type, text, time) }
            addFragment(tableFragment!!)
        }
    }

    private fun goGraph(){
        if (graphFragment != null) changeFragment(graphFragment!!)
        else {
            graphFragment = GraphFragment(
                viewModel.selectData,
                gaugesData
            ) { type, text, time -> editDateSelectData(type, text, time) }
            addFragment(graphFragment!!)
        }
    }

    private fun goRecord(){
        if (recordFragment != null) changeFragment(recordFragment!!)
        else {
            recordFragment = RecordFragment(
                viewModel.selectData,
                recordList,
                { type, text, time -> editDateSelectData(type, text, time) },
                { data ->
                    editGaugesSelectData(data)
                    menuAdapter.moveMenu(1)
                    treeFragment.toRecord(data.gaugesNum)
                })
            addFragment(recordFragment!!)
        }
    }


    override fun setupObserver() {
        super.setupObserver()

        with(viewModel) {
            setRecordList.observe(this@MainActivity) {
                recordList = it
                recordFragment?.settingRecordList(it)
            }

            setGaugesData.observe(this@MainActivity) {
                gaugesData = it
                tableFragment?.settingTableData(it)
                graphFragment?.settingGraphData(it)
            }
        }
    }

    private fun initAdapter() {
        binding.recyclerviewMenu.adapter = menuAdapter
        menuAdapter.submitList(
            listOf(
                MenuData(0, "트리", R.drawable.tree_icon, true),
                MenuData(1, "표", R.drawable.table_icon, false),
                MenuData(2, "그래프", R.drawable.graph_icon, false),
                MenuData(3, "검사기록", R.drawable.record_icon, false)
            )
        )
    }

    private fun initLayout(fieldNum: Int) {
        treeFragment = TreeFragment(fieldNum) { item ->
            editGaugesSelectData(item)
            if(item.gaugesNum != 0) menuAdapter.moveMenu(2)
        }
        supportFragmentManager.beginTransaction().replace(binding.frameLayout.id, treeFragment)
            .commit()
    }

    private fun editGaugesSelectData(data: SelectData) {
        with(viewModel) {
            selectData = data
        }
        settingSelectData()
    }

    private fun editDateSelectData(type : Int, text : String, time : Long){
        with(viewModel) {
            if(type == 0){
                selectData.fromDay = text
                selectData.startUnixTime = time
            }else{
                selectData.toDay = text
                selectData.endUnixTime = time
            }
        }
        settingSelectData()
    }


    private fun settingSelectData() {
        tableFragment?.setSelectData(viewModel.selectData)
        graphFragment?.setSelectData(viewModel.selectData)
        recordFragment?.setSelectData(viewModel.selectData)

        with(viewModel) {
            getProcessLog()
            getGaugesData()
        }
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
                supportFragmentManager.beginTransaction().add(binding.frameLayout.id, newFragment)
                    .commit()
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

    override fun onBackPressed() {
        doubleTabBack()
    }

    private fun doubleTabBack() {
        if (System.currentTimeMillis() - time >= 2000) {
            time = System.currentTimeMillis()
            showToast(resources.getString(R.string.double_tab))
        } else if (System.currentTimeMillis() - time < 2000) {
            super.onBackPressed()
            finish()
            exitProcess(0)
        }
    }

}