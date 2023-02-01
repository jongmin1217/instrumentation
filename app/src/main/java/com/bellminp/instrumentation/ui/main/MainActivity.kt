package com.bellminp.instrumentation.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.bellminp.common.timberMsg
import com.bellminp.instrumentation.InstrumentationApplication
import com.bellminp.instrumentation.R
import com.bellminp.instrumentation.databinding.ActivityMainBinding
import com.bellminp.instrumentation.model.*
import com.bellminp.instrumentation.ui.base.BaseActivity
import com.bellminp.instrumentation.ui.dialog.fieldselect.FieldListAdapter
import com.bellminp.instrumentation.ui.dialog.initsetting.InitSettingDialog
import com.bellminp.instrumentation.ui.login.LoginActivity
import com.bellminp.instrumentation.ui.main.graph.GraphFragment
import com.bellminp.instrumentation.ui.main.record.RecordFragment
import com.bellminp.instrumentation.ui.main.table.TableFragment
import com.bellminp.instrumentation.ui.main.tree.TreeFragment
import com.bellminp.instrumentation.ui.splash.SplashViewModel
import com.bellminp.instrumentation.ui.usinginfo.UsingInfoActivity
import com.bellminp.instrumentation.utils.CONNECT_DATA
import com.bellminp.instrumentation.utils.FIELD_DATA
import com.bellminp.instrumentation.utils.NAME
import com.bellminp.instrumentation.utils.TYPE
import com.bellminp.remote.utils.CONNECT_NAME
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

    private var fieldList : FieldData? = null

    private var time: Long = 0

    private val menuAdapter = MenuAdapter {
        when (it.id) {
            0 -> goTree()
            1 -> goTable()
            2 -> goGraph()
            3 -> goRecord()
        }
    }

    private val fieldAdapter = FieldListAdapter{
        viewModel.showFieldSelect.value = false

        if(viewModel.fieldNum != it.num){
            editGaugesSelectData(SelectData(
                selectSections = "",
                selectGauges = "",
                gaugesNum = 0,
                type = ""
            ))

            treeFragment.fieldNum = it.num
            treeFragment.refresh()

            with(viewModel) {
                fieldNum = it.num
                getProcessLog()
            }

            menuAdapter.moveMenu(0)
        }

    }

    override fun setupBinding() {
        binding.vm = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val num = intent.getIntExtra(TYPE, 0)
        if(intent.hasExtra(FIELD_DATA)){
            fieldList = intent.getSerializableExtra(FIELD_DATA) as FieldData
        }
        viewModel.connectInfo.value = intent.getSerializableExtra(CONNECT_DATA) as Connect
        timberMsg(intent.getSerializableExtra(CONNECT_DATA) as Connect)
        initLayout(num)
        initAdapter()
        initListener()

        with(viewModel) {
            fieldNum = num
            getProcessLog()
            getSetting(num)
        }
    }

    private fun initListener(){
        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

        binding.drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener{
            override fun onDrawerClosed(drawerView: View) {
                binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                binding.layoutTreeSetting.visibility = View.GONE
                binding.layoutGraphSetting.visibility = View.GONE
                binding.layoutFieldInfo.visibility = View.GONE

                binding.ivTreeSetting.visibility = View.VISIBLE
                binding.ivGraphSetting.visibility = View.VISIBLE
                binding.ivFieldInfo.visibility = View.VISIBLE
            }
            override fun onDrawerOpened(drawerView: View) {}
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {}
            override fun onDrawerStateChanged(newState: Int) {}
        })

        binding.tvSite.setOnClickListener {
            with(viewModel){
                fieldList?.let {
                    showFieldSelect.value = !(showFieldSelect.value?:false)
                }
            }
        }

        binding.layoutDots.setOnClickListener {
            binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            binding.drawerLayout.openDrawer(GravityCompat.END)
        }

        binding.layoutBar.setOnClickListener {
            binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        binding.tvLogout.setOnClickListener {
            viewModel.logout()
        }

        binding.tvTreeSetting.setOnClickListener {
            if(binding.layoutTreeSetting.isVisible){
                binding.layoutTreeSetting.visibility = View.GONE
                binding.ivTreeSetting.visibility = View.VISIBLE
            }
            else{
                binding.layoutTreeSetting.visibility = View.VISIBLE
                binding.ivTreeSetting.visibility = View.GONE
            }
        }

        binding.tvGraphSetting.setOnClickListener {
            if(binding.layoutGraphSetting.isVisible){
                binding.layoutGraphSetting.visibility = View.GONE
                binding.ivGraphSetting.visibility = View.VISIBLE
            }
            else{
                binding.layoutGraphSetting.visibility = View.VISIBLE
                binding.ivGraphSetting.visibility = View.GONE
            }
        }

        binding.tvFieldInfo.setOnClickListener {
            if(binding.layoutFieldInfo.isVisible){
                binding.layoutFieldInfo.visibility = View.GONE
                binding.ivFieldInfo.visibility = View.VISIBLE
            }
            else{
                binding.layoutFieldInfo.visibility = View.VISIBLE
                binding.ivFieldInfo.visibility = View.GONE
            }
        }

        binding.switchSms.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setSetting(isChecked)
            binding.tvFieldInfoSmsValue.text = if(isChecked) "ON" else "OFF"
        }


        binding.switchRotate.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setLocalData(rotate = isChecked)
        }

        binding.switch1.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setLocalData(treeSite = isChecked)
            binding.tv1.text = if(isChecked) "ON" else "OFF"
            InstrumentationApplication.mInstance.treeSite = isChecked
            refreshTree()
        }

        binding.switch2.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setLocalData(treeSection = isChecked)
            binding.tv2.text = if(isChecked) "ON" else "OFF"
            InstrumentationApplication.mInstance.treeSection = isChecked
            refreshTree()
        }

        binding.switch3.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setLocalData(treeGroup = isChecked)
            binding.tv3.text = if(isChecked) "ON" else "OFF"
            InstrumentationApplication.mInstance.treeGroup = isChecked
            refreshTree()
        }

        binding.switch4.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setLocalData(treeGauges = isChecked)
            binding.tv4.text = if(isChecked) "ON" else "OFF"
            InstrumentationApplication.mInstance.treeGauges = isChecked
            refreshTree()
        }

        binding.cbDate1.setOnCheckedChangeListener { _, b ->
            if(b){
                binding.cbDate1.isEnabled = false
                binding.cbDate2.isChecked = false
                viewModel.setLocalData(graphDate = 0)
                InstrumentationApplication.mInstance.graphDate = 0
                refreshGraph()
            }else binding.cbDate1.isEnabled = true
        }
        binding.cbDate2.setOnCheckedChangeListener { _, b ->
            if(b){
                binding.cbDate2.isEnabled = false
                binding.cbDate1.isChecked = false
                viewModel.setLocalData(graphDate = 1)
                InstrumentationApplication.mInstance.graphDate = 1
                refreshGraph()
            }else binding.cbDate2.isEnabled = true
        }

        binding.cbPoint1.setOnCheckedChangeListener { _, b ->
            if(b){
                binding.cbPoint1.isEnabled = false
                binding.cbPoint2.isChecked = false
                binding.cbPoint3.isChecked = false
                viewModel.setLocalData(graphPoint = 0)
                InstrumentationApplication.mInstance.graphPoint = 0
                refreshGraph()
            }else binding.cbPoint1.isEnabled = true
        }
        binding.cbPoint2.setOnCheckedChangeListener { _, b ->
            if(b){
                binding.cbPoint2.isEnabled = false
                binding.cbPoint1.isChecked = false
                binding.cbPoint3.isChecked = false
                viewModel.setLocalData(graphPoint = 1)
                InstrumentationApplication.mInstance.graphPoint = 1
                refreshGraph()
            }else binding.cbPoint2.isEnabled = true
        }
        binding.cbPoint3.setOnCheckedChangeListener { _, b ->
            if(b){
                binding.cbPoint3.isEnabled = false
                binding.cbPoint1.isChecked = false
                binding.cbPoint2.isChecked = false
                viewModel.setLocalData(graphPoint = 2)
                InstrumentationApplication.mInstance.graphPoint = 2
                refreshGraph()
            }else binding.cbPoint3.isEnabled = true
        }

        binding.tvInitSetting.setOnClickListener {
            InitSettingDialog{
                binding.drawerLayout.closeDrawer(GravityCompat.END)
                binding.switchRotate.isChecked = true
                binding.switch1.isChecked = true
                binding.switch2.isChecked = true
                binding.switch3.isChecked = true
                binding.switch4.isChecked = true
                binding.cbDate1.isChecked = true
                binding.cbDate2.isChecked = false
                binding.cbPoint1.isChecked = true
                binding.cbPoint2.isChecked = false
                binding.cbPoint3.isChecked = false

                treeFragment.refreshTree()
                graphFragment?.refreshGraph()
            }.show(supportFragmentManager,"InitSettingDialog")
        }

        binding.tvSendMail.setOnClickListener {
            sendMain()
        }

        binding.tvUsingInfo.setOnClickListener {
            startActivity(Intent(this@MainActivity,UsingInfoActivity::class.java))
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun sendMain(){
        val emailAddress = "arado77@hanmail.net"
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            type = "text/plain"
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf(emailAddress))
            putExtra(Intent.EXTRA_SUBJECT, "NSRMS_${CONNECT_NAME}_${binding.tvSite.text}_사용자의견")
        }

        try {
            startActivity(intent)
        }catch (e: Exception){
            showToast("메일을 전송할 수 없습니다.")
        }
    }

    private fun setTitle(text : String){
        binding.tvSite.text = text
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
                gaugesData,
                binding.frameLayout.height
            ) { type, text, time -> editDateSelectData(type, text, time) }
            addFragment(graphFragment!!)
        }
    }

    private fun goRecord(){
        if (recordFragment != null) changeFragment(recordFragment!!)
        else {
            recordFragment = RecordFragment(
                viewModel.recordSelectData,
                recordList,
                { type, text, time -> editRecordSelectData(type, text, time) },
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

            setHeader.observe(this@MainActivity) {
                setTitle(it)
            }

            initTreeSetting.observe(this@MainActivity){
                with(InstrumentationApplication.mInstance){
                    treeSite = it[1]
                    treeSection = it[2]
                    treeGroup = it[3]
                    treeGauges = it[4]
                }
                binding.switchRotate.isChecked = it[0]

                binding.switch1.isChecked = it[1]
                binding.switch2.isChecked = it[2]
                binding.switch3.isChecked = it[3]
                binding.switch4.isChecked = it[4]

                binding.tv1.text = if(it[1]) "ON" else "OFF"
                binding.tv2.text = if(it[2]) "ON" else "OFF"
                binding.tv3.text = if(it[3]) "ON" else "OFF"
                binding.tv4.text = if(it[4]) "ON" else "OFF"
            }

            initGraphSetting.observe(this@MainActivity){
                with(InstrumentationApplication.mInstance){
                    graphDate = it[0]
                    graphPoint = it[1]
                }
                when(it[0]){
                    0 -> {
                        binding.cbDate1.isChecked = true
                        binding.cbDate2.isChecked = false
                    }

                    1 -> {
                        binding.cbDate1.isChecked = false
                        binding.cbDate2.isChecked = true
                    }
                }

                when(it[1]){
                    0 -> {
                        binding.cbPoint1.isChecked = true
                        binding.cbPoint2.isChecked = false
                        binding.cbPoint3.isChecked = false
                    }

                    1 -> {
                        binding.cbPoint1.isChecked = false
                        binding.cbPoint2.isChecked = true
                        binding.cbPoint3.isChecked = false
                    }

                    2 -> {
                        binding.cbPoint1.isChecked = false
                        binding.cbPoint2.isChecked = false
                        binding.cbPoint3.isChecked = true
                    }
                }
            }
        }
    }

    private fun refreshTree(){
        treeFragment.refreshTree()
    }

    private fun refreshGraph(){
        graphFragment?.refreshGraph()
    }

    private fun initAdapter() {
        binding.recyclerviewField.adapter = fieldAdapter
        binding.recyclerviewMenu.adapter = menuAdapter

        fieldList?.let {
            fieldAdapter.submitList(it.data)
        }

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

    private fun editRecordSelectData(type : Int, text : String, time : Long){
        with(viewModel) {
            if(type == 0){
                recordSelectData.fromDay = text
                recordSelectData.startUnixTime = time
            }else{
                recordSelectData.toDay = text
                recordSelectData.endUnixTime = time
            }
        }
        settingRecordSelectData()
    }


    private fun settingSelectData() {
        with(viewModel) {
            tableFragment?.setSelectData(selectData)
            graphFragment?.setSelectData(selectData)
            getGaugesData()
        }
    }

    private fun settingRecordSelectData() {
        with(viewModel) {
            recordFragment?.setSelectData(recordSelectData)
            getProcessLog()
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
        when{
            binding.layoutGraphSetting.isVisible -> binding.layoutGraphSetting.visibility = View.GONE
            binding.layoutTreeSetting.isVisible -> binding.layoutTreeSetting.visibility = View.GONE
            binding.drawerLayout.isDrawerOpen(GravityCompat.END) -> binding.drawerLayout.closeDrawer(GravityCompat.END)
            binding.drawerLayout.isDrawerOpen(GravityCompat.START) -> binding.drawerLayout.closeDrawer(GravityCompat.START)
            else -> doubleTabBack()
        }
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