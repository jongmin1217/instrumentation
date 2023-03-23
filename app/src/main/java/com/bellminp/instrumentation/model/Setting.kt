package com.bellminp.instrumentation.model

data class Setting(
    val code : Int,
    val message : String,
    val tnfieldchkSMS : Int,
    val lorachk : Int,
    val apiDataYuji : Int
){
    fun loraText() = if(lorachk == 1) "ON" else "OFF"
    fun smsText() = if(tnfieldchkSMS == 1) "ON" else "OFF"
}