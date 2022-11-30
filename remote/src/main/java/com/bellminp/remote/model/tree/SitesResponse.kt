package com.bellminp.remote.model.tree

data class SitesResponse(
    val code : Int,
    val message : String,
    val list : List<SitesListResponse>?
)

data class SitesListResponse(
    val num : Int,
    val fieldNum : Int,
    val fieldName : String,
    val name : String,
    val managenum : String
)