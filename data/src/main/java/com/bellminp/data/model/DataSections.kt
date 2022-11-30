package com.bellminp.data.model

data class DataSections(
    val code : Int,
    val message : String,
    val list : List<DataSectionsList>?
)

data class DataSectionsList(
    val num : Int,
    val siteNum : Int,
    val name : String,
    val managenum : String
)

