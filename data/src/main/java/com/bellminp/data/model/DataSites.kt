package com.bellminp.data.model

data class DataSites(
    val code : Int,
    val message : String,
    val list : List<DataSitesList>?
)

data class DataSitesList(
    val num : Int,
    val fieldNum : Int,
    val fieldName : String,
    val name : String,
    val managenum : String
)