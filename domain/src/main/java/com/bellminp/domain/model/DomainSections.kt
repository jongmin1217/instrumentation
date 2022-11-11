package com.bellminp.domain.model

data class DomainSections(
    val code : Int,
    val message : String,
    val list : List<DomainSectionsList>?
)

data class DomainSectionsList(
    val num : Int,
    val siteNum : Int,
    val name : String,
    val managenum : String
)

