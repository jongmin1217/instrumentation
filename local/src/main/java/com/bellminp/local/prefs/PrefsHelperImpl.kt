package com.bellminp.local.prefs

import android.content.Context
import androidx.core.content.edit
import com.bellminp.data.model.DataAllGauges
import com.bellminp.data.model.DataAutoLogin
import com.bellminp.local.utils.*
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

    override var admin: Boolean
        get() = prefs.getBoolean(ADMIN, false)
        set(value) {
            prefs.edit {
                putBoolean(ADMIN, value)
            }
        }

    override var rotate: Boolean
        get() = prefs.getBoolean(ROTATE, true)
        set(value) {
            prefs.edit {
                putBoolean(ROTATE, value)
            }
        }
    override var treeSite: Boolean
        get() = prefs.getBoolean(TREE_SITE, true)
        set(value) {
            prefs.edit {
                putBoolean(TREE_SITE, value)
            }
        }
    override var treeSection: Boolean
        get() = prefs.getBoolean(TREE_SECTION, true)
        set(value) {
            prefs.edit {
                putBoolean(TREE_SECTION, value)
            }
        }
    override var treeGroup: Boolean
        get() = prefs.getBoolean(TREE_GROUP, true)
        set(value) {
            prefs.edit {
                putBoolean(TREE_GROUP, value)
            }
        }
    override var treeGauges: Boolean
        get() = prefs.getBoolean(TREE_GAUGES, true)
        set(value) {
            prefs.edit {
                putBoolean(TREE_GAUGES, value)
            }
        }
    override var graphDate: Int
        get() = prefs.getInt(GRAPH_DATE, 0)
        set(value) {
            prefs.edit {
                putInt(GRAPH_DATE, value)
            }
        }
    override var graphPoint: Int
        get() = prefs.getInt(GRAPH_POINT, 0)
        set(value) {
            prefs.edit {
                putInt(GRAPH_POINT, value)
            }
        }

    override fun initSetting() {
        prefs.edit {
            putBoolean(ROTATE,true)
            putBoolean(TREE_SITE,true)
            putBoolean(TREE_SECTION,true)
            putBoolean(TREE_GROUP,true)
            putBoolean(TREE_GAUGES,true)
            putInt(GRAPH_DATE,0)
            putInt(GRAPH_POINT, 0)
        }
    }

    override fun getAllGauges(num: Int) = prefs.getString(num.toString(),null)?:""

    override fun setAllGauges(data: DataAllGauges) {
        prefs.edit {
            putString(data.num,data.name)
        }
    }

    override fun clear(){
        prefs.edit{
            clear()
            commit()
        }
    }
}