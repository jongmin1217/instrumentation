package com.bellminp.remote.model.setting

data class SettingResponse(
    val code : Int,
    val message : String,
    val tnfieldchkSMS : Int,
    val lorachk : Int,
    val apiDataYuji : Int
)