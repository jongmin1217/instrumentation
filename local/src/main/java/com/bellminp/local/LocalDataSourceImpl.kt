package com.bellminp.local

import com.bellminp.data.local.LocalDataSource
import com.bellminp.data.model.DataAllGauges
import com.bellminp.data.model.DataAutoLogin
import com.bellminp.local.prefs.PrefsHelper
import javax.inject.Inject

class LocalDataSourceImpl@Inject constructor(
    private val prefsHelper: PrefsHelper
)  : LocalDataSource {

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

    override fun setDataAllGauges(dataAllGauges : DataAllGauges) {
        prefsHelper.setAllGauges(dataAllGauges)
    }

    override fun getDataAllGauges(num : Int) = prefsHelper.getAllGauges(num)

    override fun clear() {
        prefsHelper.clear()
    }
}