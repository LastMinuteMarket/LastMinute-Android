package com.lastminute.ui.post

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import com.lastminute.common.BaseActivity
import com.lastminute.repository.model.product.PlacementDto
import com.lastminute.repository.model.product.ProductCreateDto
import com.lastminute.repository.model.product.ProductDetailDto
import com.lastminute.ui.model.PriceTerm
import com.lastminute.ui.R
import com.lastminute.ui.databinding.ActivityPostBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDate
import java.time.LocalDateTime

class PostActivity() : BaseActivity<ActivityPostBinding>(R.layout.activity_post) {

    private val postViewModel: PostViewModel by viewModel()

    private lateinit var placementListAdapter: PlacementListAdapter
    private lateinit var priceListAdapter: PriceListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAdapter()
        initPlacement()

        observeData()
        observePaidPrice()

        initSubmitBtn()
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
        postViewModel.result.observe(this) {
            placementListAdapter.setData(it)
        }

        postViewModel.placement.observe(this) {
            binding.tvPlace.text = it.title
        }
    }

    private fun observePaidPrice() {
        binding.etPaid.addTextChangedListener {
            binding.clPriceToday.originPrice = it?.toString()?.toInt() ?: 0
        }
    }

    private fun initSubmitBtn() {
        binding.btnPostApply.setOnClickListener {
            val reservedDate = binding.btnDate.date
            val hour = binding.tvTime.hour
            val minute = binding.tvTime.minute
            val reservationType =
                if (binding.btnPrepaid.isSelected) "PREPAID"
                else "DEPOSIT"

            postViewModel.postProduct(
                ProductCreateDto(
                    placement = PlacementDto(
                        title = postViewModel.placement.value!!.title,
                        roadAddress = postViewModel.placement.value!!.roadAddress,
                        pointX = postViewModel.placement.value!!.lat,
                        pointY = postViewModel.placement.value!!.lot
                    ),
                    detail = ProductDetailDto(
                        menu = binding.etMenu.text.toString(),
                        description = binding.etPostAdditional.text.toString(),
                        reservedPeoples = binding.etNumPeoples.text.toString().toInt(),
                        reservedTime = LocalDateTime.of(reservedDate.year, reservedDate.month, reservedDate.dayOfMonth, hour, minute),
                        reservationType = reservationType,
                        pricePaid = binding.etPaid.text.toString().toInt(),
                        priceNow = binding.clPriceToday.priceData.price.get()!!.toInt()
                    ),
                    priceSchedules = emptyList()
                )
            )

            finish()
        }
    }

}