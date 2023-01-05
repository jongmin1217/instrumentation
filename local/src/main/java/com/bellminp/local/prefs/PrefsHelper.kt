package com.bellminp.local.prefs

import com.bellminp.data.model.DataAllGauges
import com.bellminp.data.model.DataAutoLogin


interface PrefsHelper {
    var dataAutoLogin : DataAutoLogin
    var token : String
    var admin : Boolean
    var rotate : Boolean
    var treeSite : Boolean
    var treeSection : Boolean
    var treeGroup : Boolean
    var treeGauges : Boolean
    var graphDate : Int
    var graphPoint : Int
    fun initSetting()
    fun getAllGauges(num : Int) : String
    fun setAllGauges(data : DataAllGauges)
    fun clear()
}