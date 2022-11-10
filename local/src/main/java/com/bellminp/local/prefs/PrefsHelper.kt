package com.bellminp.local.prefs

import com.bellminp.data.model.DataAutoLogin


interface PrefsHelper {
    var dataAutoLogin : DataAutoLogin
    var token : String
    fun clear()
}