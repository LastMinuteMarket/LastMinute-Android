package com.lastminute.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.lastminute.aos.databinding.ViewSearchBarBinding

class SearchBar (
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val binding = ViewSearchBarBinding.inflate(LayoutInflater.from(context))


}