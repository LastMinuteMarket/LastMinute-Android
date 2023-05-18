package com.lastminute.util

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.changeFragment(layoutRes: Int, fragment: Fragment) {
    supportFragmentManager
        .beginTransaction()
        .replace(layoutRes, fragment)
        .commit()
}