package com.lastminute.ui.search

import android.bluetooth.BluetoothA2dp
import android.content.Intent
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
import com.lastminute.ui.detail.DetailActivity
import com.lastminute.util.MapUtil.markAt
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.MarkerIcons
import java.time.LocalDateTime

class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search), OnMapReadyCallback {
    private lateinit var naverMap: NaverMap
    private lateinit var productListAdapter: ProductListAdapter

    // 더미 데이터
    val dummy = listOf<ProductSummary>(
        ProductSummary(1, 37.5061 , 126.9582,"펀방탈출", "고장난모니터 (2인)", LocalDateTime.of(2023, 5, 12, 19, 0), "40,000원", "30,000원", "23", ProductSummary.ProductType.ALL_PAID),
        ProductSummary(2, 37.5065 , 126.9581, "오마카세 오이시", "런치코스 (2인)", LocalDateTime.of(2023, 5, 15, 20, 0), "100,000원", "70,000원", "12", ProductSummary.ProductType.BOOKED),
        ProductSummary(3, 37.5062 , 126.9585, "맛있는 레스토랑", "스테이크 코스 (2인)", LocalDateTime.of(2023, 5, 13, 12, 0), "50,000원", "10,000원", "7", ProductSummary.ProductType.ALL_PAID),
        ProductSummary(4, 37.5070 , 126.9584,"우리 원데이클래스", "그림 (3인)", LocalDateTime.of(2023, 5, 13, 12, 0), "50,000원", "10,000원", "7", ProductSummary.ProductType.ALL_PAID),
        ProductSummary(5, 37.5069 , 126.9586,"니스툴", "(2인)", LocalDateTime.of(2023, 5, 13, 12, 0), "50,000원", "10,000원", "7", ProductSummary.ProductType.ALL_PAID),
        ProductSummary(6, 37.5064 , 126.9583,"은희네 닭떡볶이", "(2인)", LocalDateTime.of(2023, 5, 13, 12, 0), "50,000원", "10,000원", "7", ProductSummary.ProductType.ALL_PAID),
        ProductSummary(7, 37.5066 , 126.9583,"맛있는 집", "세트메뉴 (3인)", LocalDateTime.of(2023, 5, 13, 12, 0), "50,000원", "10,000원", "7", ProductSummary.ProductType.ALL_PAID),
        ProductSummary(8, 37.5062 , 126.9581,"램스 키친", "솔로 코스 (1인)", LocalDateTime.of(2023, 5, 13, 12, 0), "50,000원", "10,000원", "7", ProductSummary.ProductType.ALL_PAID),
        ProductSummary(9, 37.5068 , 126.9579, "에어 연극", "타임 (2인)", LocalDateTime.of(2023, 5, 13, 12, 0), "50,000원", "10,000원", "7", ProductSummary.ProductType.ALL_PAID),
        ProductSummary(10, 37.5060 , 126.9578,"가나다라", "마바 (4인)", LocalDateTime.of(2023, 5, 13, 12, 0), "50,000원", "10,000원", "7", ProductSummary.ProductType.ALL_PAID),
        ProductSummary(11, 37.5074 , 126.9588,"사아자차", "카타 (3인)", LocalDateTime.of(2023, 5, 13, 12, 0), "50,000원", "10,000원", "7", ProductSummary.ProductType.ALL_PAID)
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadNaverMap()
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
        productListAdapter = ProductListAdapter {
//            val intent = Intent(context, DetailActivity::class.java)
//            startActivity(intent)
        }
        binding.rvProducts.adapter = productListAdapter

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

        dummy.stream()
            .forEach { it -> naverMap.markAt(it.lat, it.lot, it.title, it.menu) }
    }

    private fun loadNaverMap() {
        val fm = requireActivity().supportFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map) as com.naver.maps.map.MapFragment?
            ?: com.naver.maps.map.MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map, it).commit()
            }

        mapFragment.getMapAsync(this)
    }


}