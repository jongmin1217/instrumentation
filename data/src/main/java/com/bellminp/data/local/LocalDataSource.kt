package com.bellminp.data.local

import com.bellminp.data.model.DataAllGauges
import com.bellminp.data.model.DataAutoLogin
import com.bellminp.domain.model.DomainAllGauges

interface LocalDataSource {

    fun setAutoLogin(autoLogin : DataAutoLogin)

    fun getAutoLogin() : DataAutoLogin

    fun setToken(token : String)

    fun getToken() : String

    fun setAdmin(admin : Boolean)

    fun getAdmin() : Boolean

    fun setDataAllGauges(dataAllGauges : DataAllGauges)

    fun getDataAllGauges(num : Int) : String
    
    fun getRotate() : Boolean
    fun setRotate(value : Boolean)
    
    fun getTreeSite() : Boolean
    fun setTreeSite(value : Boolean)

    fun getTreeSection() : Boolean
    fun setTreeSection(value : Boolean)

    fun getTreeGroup() : Boolean
    fun setTreeGroup(value : Boolean)

    fun getTreeGauges() : Boolean
    fun setTreeGauges(value : Boolean)

    fun getGraphDate() : Int
    fun setGraphDate(value : Int)

    fun getGraphPoint() : Int
    fun setGraphPoint(value : Int)

    fun initSetting()

    fun clear()
}