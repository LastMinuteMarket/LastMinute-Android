package com.lastminute.ui.search

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.transition.Visibility
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.widget.Toast
import androidx.annotation.UiThread
import com.google.android.gms.location.*
import com.lastminute.common.BaseFragment
import com.lastminute.ui.model.ProductSummary
import com.lastminute.ui.R
import com.lastminute.ui.databinding.FragmentSearchBinding
import com.lastminute.ui.model.Point
import com.lastminute.ui.product.ProductActivity
import com.lastminute.util.MapUtil.markAt
import com.lastminute.util.MapUtil.scrollMove
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.util.FusedLocationSource
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDateTime
import java.util.jar.Manifest
import kotlin.streams.toList

class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search), OnMapReadyCallback {
    val permissions = arrayOf(
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    )

    private val searchViewModel: SearchViewModel by viewModel()

    private lateinit var locationSource: FusedLocationSource
    private lateinit var naverMap: NaverMap
    private lateinit var productListAdapter: ProductListAdapter

    // 더미 데이터
//    val dummy = listOf<ProductSummary>(
//        ProductSummary(1, 37.5061 , 126.9582,"펀방탈출", "고장난모니터 (2인)", LocalDateTime.of(2023, 5, 12, 19, 0), "40,000원", "30,000원", "23", ProductSummary.ProductType.PREPAID),
//        ProductSummary(2, 37.5065 , 126.9581, "오마카세 오이시", "런치코스 (2인)", LocalDateTime.of(2023, 5, 15, 20, 0), "100,000원", "70,000원", "12", ProductSummary.ProductType.DEPOSIT),
//        ProductSummary(3, 37.5062 , 126.9585, "맛있는 레스토랑", "스테이크 코스 (2인)", LocalDateTime.of(2023, 5, 13, 12, 0), "50,000원", "10,000원", "7", ProductSummary.ProductType.PREPAID),
//        ProductSummary(4, 37.5070 , 126.9584,"우리 원데이클래스", "그림 (3인)", LocalDateTime.of(2023, 5, 13, 12, 0), "50,000원", "10,000원", "7", ProductSummary.ProductType.PREPAID),
//        ProductSummary(5, 37.5069 , 126.9586,"니스툴", "(2인)", LocalDateTime.of(2023, 5, 13, 12, 0), "50,000원", "10,000원", "7", ProductSummary.ProductType.PREPAID),
//        ProductSummary(6, 37.5064 , 126.9583,"은희네 닭떡볶이", "(2인)", LocalDateTime.of(2023, 5, 13, 12, 0), "50,000원", "10,000원", "7", ProductSummary.ProductType.PREPAID),
//        ProductSummary(7, 37.5066 , 126.9583,"맛있는 집", "세트메뉴 (3인)", LocalDateTime.of(2023, 5, 13, 12, 0), "50,000원", "10,000원", "7", ProductSummary.ProductType.PREPAID),
//        ProductSummary(8, 37.5062 , 126.9581,"램스 키친", "솔로 코스 (1인)", LocalDateTime.of(2023, 5, 13, 12, 0), "50,000원", "10,000원", "7", ProductSummary.ProductType.PREPAID),
//        ProductSummary(9, 37.5068 , 126.9579, "에어 연극", "타임 (2인)", LocalDateTime.of(2023, 5, 13, 12, 0), "50,000원", "10,000원", "7", ProductSummary.ProductType.PREPAID),
//        ProductSummary(10, 37.5060 , 126.9578,"가나다라", "마바 (4인)", LocalDateTime.of(2023, 5, 13, 12, 0), "50,000원", "10,000원", "7", ProductSummary.ProductType.PREPAID),
//        ProductSummary(11, 37.5074 , 126.9588,"사아자차", "카타 (3인)", LocalDateTime.of(2023, 5, 13, 12, 0), "50,000원", "10,000원", "7", ProductSummary.ProductType.PREPAID)
//    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().requestPermissions(permissions, LOCATION_PERMISSION_REQUEST_CODE)
        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
        loadNaverMap()
        initAdapter()
        observeData()
    }

    private fun observeData() {
        searchViewModel.products.observe(viewLifecycleOwner) { it ->
            productListAdapter.clearData()
            productListAdapter.putData(it)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            if (!locationSource.isActivated) {
                naverMap.locationTrackingMode = LocationTrackingMode.None
            }
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun initAdapter() {
        productListAdapter = ProductListAdapter { productId ->
            val intent = Intent(context, ProductActivity::class.java)
            intent.putExtra("productId", productId)
            startActivity(intent)
        }
        binding.rvProducts.adapter = productListAdapter
    }

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationCallBack: LocationCallback

    /**
     * naver map 로딩
     */
    @UiThread
    override fun onMapReady(map: NaverMap) {
        this.naverMap = map
        this.naverMap.mapType = NaverMap.MapType.Navi
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        map.uiSettings.isLocationButtonEnabled = true

//        naverMap.locationTrackingMode = LocationTrackingMode.Follow
        naverMap.locationSource = locationSource

        naverMap.addOnLocationChangeListener { location ->
            searchViewModel.point.postValue((Point(location.latitude, location.longitude)))
        }

        searchViewModel.products.observe(viewLifecycleOwner) { data ->
            data.stream().forEach {
                naverMap.markAt(it.lat, it.lot, it.title, it.menu)
            }

//            if (data.isNotEmpty())
//                naverMap.scrollMove(data[0].lat, data[0].lot)

        }

        setUpdateLocationListner()
    }

    @SuppressLint("MissingPermission")
    fun setUpdateLocationListner() {
        val locationRequest = LocationRequest.create()
        locationRequest.run {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY //높은 정확도
            interval = 1500 //1초에 한번씩 GPS 요청
        }

        locationCallBack = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
                for ((i, location) in locationResult.locations.withIndex()) {
                    Log.d("location: ", "${location.latitude}, ${location.longitude}")
//                  // TODO : lat, lon 바뀌었음..
                    searchViewModel.point.postValue(Point(lat = location.longitude, lot = location.latitude))
                }
            }
        }

        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            locationCallBack,
            Looper.myLooper()
        )
    }

    private fun loadNaverMap() {
        val fm = requireActivity().supportFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map) as com.naver.maps.map.MapFragment?
            ?: com.naver.maps.map.MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map, it).commit()
            }

        mapFragment.getMapAsync(this)
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }

}