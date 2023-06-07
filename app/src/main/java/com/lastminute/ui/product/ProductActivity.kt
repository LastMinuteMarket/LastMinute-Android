package com.lastminute.ui.product

import android.os.Bundle
import androidx.annotation.UiThread
import com.lastminute.common.BaseActivity
import com.lastminute.model.internal.ProductDetail
import com.lastminute.ui.R
import com.lastminute.ui.databinding.ActivityDetailBinding
import com.lastminute.util.MapUtil.markAt
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import java.time.LocalDateTime

class ProductActivity : BaseActivity<ActivityDetailBinding>(R.layout.activity_detail), OnMapReadyCallback {
    private lateinit var naverMap: NaverMap
    private val dummy = ProductDetail(
        1,
        "펀방탈출",
        "고장난모니터",
        2,
        "서울특별시 동작구 흑석로9길 95",
        37.5061 , 126.9582,
        2,
        "흑석방탈러",
        LocalDateTime.now(),
        true,
        40000,
        30000,
        "여기 재밌어요! 고장난에어컨 꿀잼이었슴\n" +
                "예약하기 힘든 곳이지만 사정이 생겨서 양도합니다!"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadNaverMap()
        setDummy()
    }

    override fun onDestroy() {
        super.onDestroy()
        naverMap.uiSettings.isZoomControlEnabled = true
        naverMap.uiSettings.isLocationButtonEnabled = true
    }

    private fun setDummy() {
        binding.data = dummy
    }

    @UiThread
    override fun onMapReady(map: NaverMap) {
        this.naverMap = map
        this.naverMap.mapType = NaverMap.MapType.Navi
        map.uiSettings.isLocationButtonEnabled = true

        naverMap.markAt(dummy.lat, dummy.lot, dummy.title, dummy.menu + " (" + dummy.numPeoples + "인)")

        val cameraUpdate = CameraUpdate.scrollTo(LatLng(dummy.lat, dummy.lot))
        naverMap.moveCamera(cameraUpdate)
        val zoomUpdate = CameraUpdate.zoomIn()
        naverMap.moveCamera(zoomUpdate)

        naverMap.uiSettings.isLocationButtonEnabled = false
        naverMap.uiSettings.isZoomControlEnabled = false
    }

    private fun loadNaverMap() {
        val fm = supportFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map) as com.naver.maps.map.MapFragment?
            ?: com.naver.maps.map.MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map, it).commit()
            }

        mapFragment.getMapAsync(this)
    }
}