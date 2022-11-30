package com.bellminp.local.prefs

import com.bellminp.data.model.DataAllGauges
import com.bellminp.data.model.DataAutoLogin


interface PrefsHelper {
    var dataAutoLogin : DataAutoLogin
    var token : String
    var admin : Boolean
    fun getAllGauges(num : Int) : String
    fun setAllGauges(data : DataAllGauges)
    fun clear()
}