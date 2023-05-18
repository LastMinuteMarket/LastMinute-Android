package com.lastminute.ui.search

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.UiThread
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.lastminute.common.BaseFragment
import com.lastminute.model.internal.ProductSummary
import com.lastminute.ui.R
import com.lastminute.ui.databinding.FragmentSearchBinding
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.MarkerIcons
import java.time.LocalDateTime

class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search), OnMapReadyCallback {
    private lateinit var naverMap: NaverMap
    private lateinit var productListAdapter: ProductListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()

//        // Get a reference to the MotionLayout, which is acting as our bottom sheet
//        val motionLayout = binding.mlProducts
//
//        // Get a reference to the layout params
//        val layoutParams = motionLayout.layoutParams as ConstraintLayout.LayoutParams
//
//        // Get a reference to the BottomSheetBehaviour
//        val bottomSheetBehavior = layoutParams.behavior as BottomSheetBehavior
//
//        // Add a callback to the bottom sheet so we can get the slide offset
//        bottomSheetBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
//            override fun onSlide(bottomSheet: View, slideOffset: Float) {
//                // When the slide offset changes, set it as the progress of the motion layout.
//                // This "transitions" between the start and end sets of constraints as the user slides the sheet.
//                motionLayout.progress = slideOffset
//            }
//
//            override fun onStateChanged(bottomSheet: View, newState: Int) {}
//        })
    }

    private fun initAdapter() {
        productListAdapter = ProductListAdapter {  }
        binding.rvProducts.adapter = productListAdapter

        // 더미 데이터
        val dummy = listOf<ProductSummary>(
            ProductSummary("펀방탈출", "고장난모니터 (2인)", LocalDateTime.of(2023, 5, 12, 19, 0), "40,000원", "30,000원", "23", ProductSummary.ProductType.ALL_PAID),
            ProductSummary("오마카세 오이시", "런치코스 (2인)", LocalDateTime.of(2023, 5, 15, 20, 0), "100,000원", "70,000원", "12", ProductSummary.ProductType.BOOKED),
            ProductSummary("가나다라", "졸려 (4인)", LocalDateTime.of(2023, 5, 13, 12, 0), "50,000원", "10,000원", "7", ProductSummary.ProductType.ALL_PAID),
            ProductSummary("가나다라", "졸려 (4인)", LocalDateTime.of(2023, 5, 13, 12, 0), "50,000원", "10,000원", "7", ProductSummary.ProductType.ALL_PAID),
            ProductSummary("가나다라", "졸려 (4인)", LocalDateTime.of(2023, 5, 13, 12, 0), "50,000원", "10,000원", "7", ProductSummary.ProductType.ALL_PAID),
            ProductSummary("가나다라", "졸려 (4인)", LocalDateTime.of(2023, 5, 13, 12, 0), "50,000원", "10,000원", "7", ProductSummary.ProductType.ALL_PAID),
            ProductSummary("가나다라", "졸려 (4인)", LocalDateTime.of(2023, 5, 13, 12, 0), "50,000원", "10,000원", "7", ProductSummary.ProductType.ALL_PAID),
            ProductSummary("가나다라", "졸려 (4인)", LocalDateTime.of(2023, 5, 13, 12, 0), "50,000원", "10,000원", "7", ProductSummary.ProductType.ALL_PAID),
            ProductSummary("가나다라", "졸려 (4인)", LocalDateTime.of(2023, 5, 13, 12, 0), "50,000원", "10,000원", "7", ProductSummary.ProductType.ALL_PAID),
            ProductSummary("가나다라", "졸려 (4인)", LocalDateTime.of(2023, 5, 13, 12, 0), "50,000원", "10,000원", "7", ProductSummary.ProductType.ALL_PAID),
            ProductSummary("가나다라", "졸려 (4인)", LocalDateTime.of(2023, 5, 13, 12, 0), "50,000원", "10,000원", "7", ProductSummary.ProductType.ALL_PAID)
        )
        productListAdapter.putData(dummy)

    }

    /**
     * naver map 로딩
     */
    @UiThread
    override fun onMapReady(map: NaverMap) {
        this.naverMap = map
        this.naverMap.mapType = NaverMap.MapType.Navi
        map.uiSettings.isLocationButtonEnabled = true

        markAt(37.5061 , 126.9580, "원조 방탈출")
        markAt(37.5070 , 126.9590, "조금비싼 레스토랑")
    }


    private fun markAt(lat: Double, lot: Double, caption: String) {
        val marker = Marker()
        marker.icon = MarkerIcons.BLACK
        marker.iconTintColor = Color.DKGRAY
        marker.position = LatLng(lat, lot)
        marker.captionText = caption
        marker.captionColor = Color.BLACK
        marker.captionHaloColor = Color.WHITE
        marker.map = naverMap
    }

    private fun loadNaverMap() {
//        val fm = supportFragmentManager
//        val mapFragment = fm.findFragmentById(R.id.map) as com.naver.maps.map.MapFragment?
//            ?: com.naver.maps.map.MapFragment.newInstance().also {
//                fm.beginTransaction().add(R.id.map, it).commit()
//            }
//
//        mapFragment.getMapAsync(this)
    }


}