package com.bellminp.local

import com.bellminp.data.local.LocalDataSource
import com.bellminp.data.model.DataAutoLogin
import com.bellminp.local.prefs.PrefsHelper
import javax.inject.Inject

class LocalDataSourceImpl@Inject constructor(
    private val prefsHelper: PrefsHelper
)  : LocalDataSource {

    override fun setAutoLogin(autoLogin: DataAutoLogin) {
        prefsHelper.dataAutoLogin = autoLogin
    }

    override fun getAutoLogin(): DataAutoLogin {
        return prefsHelper.dataAutoLogin
    }

    override fun setToken(token: String) {
        prefsHelper.token = token
    }

    override fun getToken(): String {
        return prefsHelper.token
    }

    override fun clear() {
        prefsHelper.clear()
    }
}