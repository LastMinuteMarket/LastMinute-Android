package com.lastminute.ui.post

import android.app.DatePickerDialog
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.lastminute.ui.databinding.ViewDatePickBinding
import java.time.LocalDate

class DatePickButton(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    private var date = LocalDate.now()
        get()  = field
        set(value) {
            field = value
            notifyDate()
        }

    private val binding = ViewDatePickBinding.inflate(LayoutInflater.from(context), this, true)

    private val onDateSetListener = DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
        this.date = LocalDate.of(year, month + 1, day)
        notifyDate()
    }

    init {
        notifyDate()

        binding.tvDate.setOnClickListener {
            DatePickerDialog(context, onDateSetListener, date.year, date.monthValue - 1, date.dayOfMonth)
                .show()
        }
    }

    private fun notifyDate() {
        binding.tvDate.text = dateToString(date)
    }

    companion object {
        private fun dateToString(date: LocalDate) =
            "" + date.year + "년 " + date.monthValue + "월 " + date.dayOfMonth + "일"
    }
}