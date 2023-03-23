package com.bellminp.domain.repository

import com.bellminp.domain.model.DomainAllGauges
import com.bellminp.domain.model.DomainAutoLogin
import com.bellminp.domain.model.DomainLogin

interface LocalRepository {
    fun setAutoLogin(autoLogin : DomainAutoLogin)

    fun getAutoLogin() : DomainAutoLogin

    fun setToken(token : String)

    fun getToken() : String

    fun setAdmin(admin : Boolean)

    fun getAdmin() : Boolean

    fun setDataAllGauges(dataAllGauges : DomainAllGauges)

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