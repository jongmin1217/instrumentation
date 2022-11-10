package com.bellminp.local.prefs

import android.content.Context
import androidx.core.content.edit
import com.bellminp.local.utils.AUTO_LOGIN_ID
import com.bellminp.local.utils.AUTO_LOGIN_PW
import com.bellminp.local.utils.PREFS_APP_NAME
import com.bellminp.data.model.DataAutoLogin
import com.bellminp.local.utils.TOKEN
import javax.inject.Inject

class PrefsHelperImpl @Inject constructor(applicationContext: Context) : PrefsHelper {

    private val prefs =
        applicationContext.getSharedPreferences(PREFS_APP_NAME, Context.MODE_PRIVATE)

    override var dataAutoLogin: DataAutoLogin
        get() = DataAutoLogin(
            prefs.getString(AUTO_LOGIN_ID, null),
            prefs.getString(AUTO_LOGIN_PW, null)
        )
        @Synchronized
        set(value) {
            prefs.edit {
                putString(AUTO_LOGIN_ID, value.username)
                putString(AUTO_LOGIN_PW, value.password)
            }
        }

    override var token: String
        get() = prefs.getString(TOKEN, null)?:""
        set(value) {
            prefs.edit {
                putString(TOKEN, value)
            }
        }

    override fun clear(){
        prefs.edit{
            clear()
            commit()
        }
    }
}