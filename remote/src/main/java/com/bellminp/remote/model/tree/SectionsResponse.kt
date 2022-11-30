package com.bellminp.remote.model.tree

data class SectionsResponse(
    val code : Int,
    val message : String,
    val list : List<SectionListResponse>?
)

data class SectionListResponse(
    val num : Int,
    val siteNum : Int,
    val name : String,
    val managenum : String
)

