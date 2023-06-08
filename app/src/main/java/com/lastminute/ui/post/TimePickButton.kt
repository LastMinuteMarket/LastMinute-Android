package com.lastminute.ui.post

import android.app.TimePickerDialog
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.lastminute.ui.databinding.ViewTimePickBinding
import com.lastminute.util.TextViewUtil
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TimePickButton(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    private val binding = ViewTimePickBinding.inflate(LayoutInflater.from(context), this, true)
    var hour = LocalDateTime.now().hour
    var minute = LocalDateTime.now().minute

    private val onTimeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
        this.hour = hour
        this.minute = minute
        notifyChange()
    }

    init {
        notifyChange()

        binding.tvTime.setOnClickListener {
            TimePickerDialog(context, onTimeSetListener, hour, minute, true)
                .show()
        }
    }

    private fun notifyChange() {
        binding.tvTime.text = timeFormat(hour, minute)
    }

    private fun timeFormat(hour: Int, minute: Int): String {
        return if (hour > 12) "오후 " + (hour - 12) + ":" + minute
        else "오전 $hour:$minute"
    }
}