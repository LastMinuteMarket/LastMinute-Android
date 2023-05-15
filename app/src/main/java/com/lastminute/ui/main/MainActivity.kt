package com.lastminute.ui.main

import android.os.Bundle
import com.lastminute.common.BaseActivity
import com.lastminute.ui.R
import com.lastminute.ui.databinding.ActivityMainBinding
import com.lastminute.ui.search.SearchFragment
import com.lastminute.util.changeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        binding.btNvMain.run {
            setOnItemSelectedListener { item ->
                when(item.itemId) {
                    R.id.navigation_search -> {
                        changeFragment(R.id.fragment_container_main, SearchFragment())
                    }
                }
                true
            }

            // default fragment
            selectedItemId = R.id.navigation_search
        }
    }

}