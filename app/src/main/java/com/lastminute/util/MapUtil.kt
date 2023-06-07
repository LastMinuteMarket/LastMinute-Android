package com.lastminute.util

import android.graphics.Color
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.MarkerIcons

object MapUtil {

    fun NaverMap.markAt(lat: Double, lot: Double, caption: String, subCaption: String) {
        val marker = Marker()
        marker.icon = MarkerIcons.BLACK
        marker.iconTintColor = Color.DKGRAY
        marker.position = LatLng(lat, lot)
        marker.captionText = caption
        marker.captionColor = Color.BLACK
        marker.subCaptionText = subCaption
        marker.subCaptionColor = Color.BLUE
        marker.subCaptionHaloColor = Color.rgb(200, 255, 200)
        marker.captionHaloColor = Color.WHITE
        marker.map = this
    }

    fun NaverMap.scrollMove(lat: Double, lot: Double) {
        val cameraUpdate = CameraUpdate.scrollTo(LatLng(lat, lot))
        moveCamera(cameraUpdate)
    }
}