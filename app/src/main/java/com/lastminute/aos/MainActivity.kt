package com.lastminute.aos

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.UiThread
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.MarkerIcons

class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private var naverMap: NaverMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        loadNaverMap()
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
        val fm = supportFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map, it).commit()
            }

        mapFragment.getMapAsync(this)
    }

    /**
     * naver map 로딩
     */
    @UiThread
    override fun onMapReady(map: NaverMap) {
        this.naverMap = map
        map.uiSettings.isLocationButtonEnabled = true

        markAt(37.5061 , 126.9580, "원조 방탈출")
        markAt(37.5070 , 126.9590, "조금비싼 레스토랑")
    }
}