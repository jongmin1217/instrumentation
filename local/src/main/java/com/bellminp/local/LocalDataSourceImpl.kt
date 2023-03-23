package com.bellminp.local

import com.bellminp.data.local.LocalDataSource
import com.bellminp.data.model.DataAllGauges
import com.bellminp.data.model.DataAutoLogin
import com.bellminp.local.prefs.PrefsHelper
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val prefsHelper: PrefsHelper
) : LocalDataSource {

    override fun setAutoLogin(autoLogin: DataAutoLogin) {
        prefsHelper.dataAutoLogin = autoLogin
    }

    override fun getAutoLogin() = prefsHelper.dataAutoLogin

    override fun setToken(token: String) {
        prefsHelper.token = token
    }

    override fun getToken() = prefsHelper.token

    override fun setAdmin(admin: Boolean) {
        prefsHelper.admin = admin
    }

    override fun getAdmin() = prefsHelper.admin

    override fun setDataAllGauges(dataAllGauges: DataAllGauges) {
        prefsHelper.setAllGauges(dataAllGauges)
    }

    override fun getDataAllGauges(num: Int) = prefsHelper.getAllGauges(num)

    override fun getRotate() = prefsHelper.rotate

    override fun setRotate(value: Boolean) {
        prefsHelper.rotate = value
    }

    override fun getTreeSite() = prefsHelper.treeSite

    override fun setTreeSite(value: Boolean) {
        prefsHelper.treeSite = value
    }

    override fun getTreeSection() = prefsHelper.treeSection

    override fun setTreeSection(value: Boolean) {
        prefsHelper.treeSection = value
    }

    override fun getTreeGroup() = prefsHelper.treeGroup

    override fun setTreeGroup(value: Boolean) {
        prefsHelper.treeGroup = value
    }

    override fun getTreeGauges() = prefsHelper.treeGauges

    override fun setTreeGauges(value: Boolean) {
        prefsHelper.treeGauges = value
    }

    override fun getGraphDate() = prefsHelper.graphDate

    override fun setGraphDate(value: Int) {
        prefsHelper.graphDate = value
    }

    override fun getGraphPoint() = prefsHelper.graphPoint

    override fun setGraphPoint(value: Int) {
        prefsHelper.graphPoint = value
    }

    override fun initSetting() {
        prefsHelper.initSetting()
    }

    override fun clear() {
        prefsHelper.clear()
    }
}