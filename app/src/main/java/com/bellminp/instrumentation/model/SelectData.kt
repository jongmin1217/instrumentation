package com.bellminp.instrumentation.model

import com.bellminp.instrumentation.utils.ONE_DAY
import com.bellminp.instrumentation.utils.convertTimestampToDateTerm
import com.bellminp.instrumentation.utils.convertTimestampToDateText
import com.bellminp.instrumentation.utils.getUnixTime

data class SelectData(
    var toDay : String = convertTimestampToDateText(
        getUnixTime(
            convertTimestampToDateTerm(getUnixTime()),
            false
        )
    ),
    var fromDay : String = convertTimestampToDateText(
        getUnixTime(
            convertTimestampToDateTerm(getUnixTime() - (ONE_DAY * 24)),
            true
        )
    ),
    var days : String = "24",
    var selectSections : String  = "",
    var selectGauges : String  = "",
    var startUnixTime : Long = getUnixTime(
        convertTimestampToDateTerm(getUnixTime() - (ONE_DAY * 24)),
        true
    )/1000,
    var endUnixTime : Long = getUnixTime(
        convertTimestampToDateTerm(getUnixTime()),
        false
    )/1000,
    var gaugesNum : Int = 0,
    var type : String = ""
)