package com.lastminute.ui.post

import android.os.Bundle
import android.view.View
import com.lastminute.common.BaseFragment
import com.lastminute.ui.model.PriceTerm
import com.lastminute.ui.R
import com.lastminute.ui.databinding.FragmentPostBinding
import java.time.LocalDate

class PostFragment() : BaseFragment<FragmentPostBinding>(R.layout.fragment_post) {
    private lateinit var priceListAdapter: PriceListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() {
        priceListAdapter = PriceListAdapter()
        binding.lvPostPrice.adapter = priceListAdapter

        binding.btnAddPrice.setOnClickListener {
            priceListAdapter.putData(
                PriceTerm(LocalDate.now().plusDays(1), 10000, 90, 9000)
            )
        }
    }
}