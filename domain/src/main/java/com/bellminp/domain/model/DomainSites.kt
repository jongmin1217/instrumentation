package com.bellminp.domain.model

data class DomainSites(
    val code : Int,
    val message : String,
    val list : List<DomainSitesList>?
)

data class DomainSitesList(
    val num : Int,
    val fieldNum : Int,
    val fieldName : String,
    val name : String,
    val managenum : String
)