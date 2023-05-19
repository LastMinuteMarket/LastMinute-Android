package com.lastminute.util

import android.graphics.Color
import android.graphics.Paint
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.TypedArrayUtils.getText
import androidx.databinding.BindingAdapter
import com.lastminute.LastMinuteApplication
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object TextViewUtil {
    val TIME_PATTERN = "a hh:mm"

    fun timeFormatProduct(time: LocalDateTime): String {
        return "" + time.monthValue + "월 " + time.dayOfMonth + "일 " + time.format(DateTimeFormatter.ofPattern(TIME_PATTERN))
    }
}

@BindingAdapter("showStrikeThrough")
fun TextView.showStrikeThrough(show: Boolean) {
    paintFlags =
        if (show) paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        else paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
}

@BindingAdapter("showLocalDateTime")
fun TextView.showLocalDateTime(time: LocalDateTime) {
    text = TextViewUtil.timeFormatProduct(time)
}

@BindingAdapter(value = ["given", "activateColor", "defaultColor"])
fun TextView.setTextColorWhen(given: Boolean, activateColor: Int, defaultColor: Int) {
    if (given) setTextColor(activateColor)
    else setTextColor(defaultColor)
}

@BindingAdapter("setIntegerText")
fun TextView.setIntegerText(value: Integer) {
    setText(value.toString())
}

@BindingAdapter("setPriceText")
fun TextView.setPriceText(value: Integer) {
    setText("" + value + "원")
}

@BindingAdapter(value = ["menuString", "numPeople"])
fun TextView.setBindMenu(menuString: String, numPeople: Int) {
    setText("$menuString ($numPeople 인)")
}