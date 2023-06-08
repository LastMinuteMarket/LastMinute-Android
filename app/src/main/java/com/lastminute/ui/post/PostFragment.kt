package com.lastminute.ui.post

import android.os.Bundle
import android.view.View
import com.lastminute.common.BaseFragment
import com.lastminute.ui.model.PriceTerm
import com.lastminute.ui.R
import com.lastminute.ui.databinding.FragmentPostBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDate

class PostFragment() : BaseFragment<FragmentPostBinding>(R.layout.fragment_post) {

    private val postViewModel: PostViewModel by viewModel()

    private lateinit var placementListAdapter: PlacementListAdapter
    private lateinit var priceListAdapter: PriceListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initPlacement()

        observeData()
    }

    private fun initPlacement() {
        binding.tvPlace.setOnClickListener {
            setSearchPlaceView(true)
        }

        binding.btnPlaceSearch.setOnClickListener {
            postViewModel.getPlacements(binding.etPlace.text.toString())
        }
    }

    private fun initAdapter() {
        priceListAdapter = PriceListAdapter()
        placementListAdapter = PlacementListAdapter { it ->
            postViewModel.placement.postValue(it)
            setSearchPlaceView(false)
        }

        binding.lvPostPrice.adapter = priceListAdapter
        binding.rvPlace.adapter = placementListAdapter

        binding.btnAddPrice.setOnClickListener {
            priceListAdapter.putData(
                PriceTerm(LocalDate.now().plusDays(1), 10000, 90, 9000)
            )
        }
    }

    private fun setSearchPlaceView(open: Boolean) {
        if (open)
            binding.clPlaceSearch.visibility = View.VISIBLE
        else
            binding.clPlaceSearch.visibility = View.GONE
    }

    private fun observeData() {
        postViewModel.result.observe(requireActivity()) {
            placementListAdapter.setData(it)
        }

        postViewModel.placement.observe(requireActivity()) {
            binding.tvPlace.text = it.title
        }
    }

}