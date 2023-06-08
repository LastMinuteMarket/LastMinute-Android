package com.lastminute.ui.post

import android.app.DatePickerDialog
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.lastminute.ui.databinding.ViewDatePickBinding
import java.time.LocalDate

class DatePickButton(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    private var _date = LocalDate.now()
        set(value) {
            field = value
            notifyDate()
        }
    val date: LocalDate
        get() = _date

    private val binding = ViewDatePickBinding.inflate(LayoutInflater.from(context), this, true)

    private val onDateSetListener = DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
        this._date = LocalDate.of(year, month + 1, day)
        notifyDate()
    }

    init {
        notifyDate()

        binding.tvDate.setOnClickListener {
            DatePickerDialog(context, onDateSetListener, _date.year, _date.monthValue - 1, _date.dayOfMonth)
                .show()
        }
    }

    private fun notifyDate() {
        binding.tvDate.text = dateToString(_date)
    }

    companion object {
        private fun dateToString(date: LocalDate) =
            "" + date.year + "년 " + date.monthValue + "월 " + date.dayOfMonth + "일"
    }
}