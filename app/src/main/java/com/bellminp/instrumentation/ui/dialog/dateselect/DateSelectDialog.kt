package com.bellminp.instrumentation.ui.dialog.dateselect

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.bellminp.instrumentation.R
import com.bellminp.instrumentation.databinding.DialogDateSelectBinding
import com.bellminp.instrumentation.databinding.DialogFieldSelectBinding
import com.bellminp.instrumentation.model.FieldList
import com.bellminp.instrumentation.ui.base.BaseDialog
import com.bellminp.instrumentation.ui.dialog.fieldselect.FieldListAdapter
import com.bellminp.instrumentation.ui.dialog.fieldselect.FieldSelectViewModel
import com.bellminp.instrumentation.utils.convertTimestampToDateText
import com.bellminp.instrumentation.utils.dateText
import com.bellminp.instrumentation.utils.getDateDay
import com.bellminp.instrumentation.utils.getUnixTime

class DateSelectDialog(
    private val minDate: Long?,
    private val maxDate: Long,
    private val initDate: Long,
    private val type: Int,
    private val onItemClick: ((type : Int,text: String, time: Long) -> Unit)
) : BaseDialog<DialogDateSelectBinding, DateSelectViewModel>(R.layout.dialog_date_select) {
    override val viewModel by activityViewModels<DateSelectViewModel>()


    override fun setupBinding() {
        binding.vm = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        minDate?.let { binding.calenderView.minDate = it }
        binding.calenderView.maxDate = maxDate
        binding.calenderView.date = initDate

        with(viewModel) {
            allDate = convertTimestampToDateText(initDate)
            year.value = String.format("%s년", convertTimestampToDateText(initDate).split("/")[0])
            val weekText = when (getDateDay(
                String.format(
                    "%s%s%s",
                    convertTimestampToDateText(initDate).split("/")[0],
                    convertTimestampToDateText(initDate).split("/")[1],
                    convertTimestampToDateText(initDate).split("/")[2]
                ), "yyyyMMdd"
            )) {
                1 -> "일"
                2 -> "월"
                3 -> "화"
                4 -> "수"
                5 -> "목"
                6 -> "금"
                else -> "토"
            }
            date.value = String.format(
                "%s월 %s일 (%s)",
                convertTimestampToDateText(initDate).split("/")[1],
                convertTimestampToDateText(initDate).split("/")[2],
                weekText
            )
        }

        binding.calenderView.setOnDateChangeListener { _, i, i2, i3 ->
            with(viewModel) {
                allDate = String.format("%d/%s/%s", i, dateText(i2 + 1), dateText(i3))
                year.value = String.format("%d년", i)
                val weekText = when (getDateDay(
                    String.format("%d%s%s", i, dateText(i2 + 1), dateText(i3)),
                    "yyyyMMdd"
                )) {
                    1 -> "일"
                    2 -> "월"
                    3 -> "화"
                    4 -> "수"
                    5 -> "목"
                    6 -> "금"
                    else -> "토"
                }
                date.value = String.format("%d월 %d일 (%s)", i2 + 1, i3, weekText)
            }
        }

        binding.tvCancel.setOnClickListener {
            dismiss()
        }

        binding.tvApply.setOnClickListener {
            onItemClick(type,viewModel.allDate,getUnixTime(viewModel.allDate.replace("/","."),type == 0)/1000)
            dismiss()
        }

    }



}