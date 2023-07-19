package com.mibaldi.monumentoszaragoza.ui.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener
import com.google.android.gms.maps.GoogleMap.OnMapClickListener
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.mibaldi.monumentoszaragoza.R
import com.mibaldi.monumentoszaragoza.databinding.ActivityMainBinding
import com.mibaldi.monumentoszaragoza.ui.common.errorToString
import com.mibaldi.monumentoszaragoza.ui.common.goToDetail
import com.mibaldi.monumentoszaragoza.ui.common.launchAndCollect
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnMapReadyCallback,OnMapClickListener,OnInfoWindowClickListener{
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    var googleMap: GoogleMap? = null
    private val markersMap: HashMap<Int, Marker?> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)


    }

    private fun addCustomMarker(id: Int, latLng: LatLng, title: String?) {
        val marker = googleMap?.addMarker(
            MarkerOptions()
                .position(latLng)
                .title(title)
                .snippet("")
        )
        markersMap[id] = marker
    }

    override fun onMapReady(map: GoogleMap) {
        this.googleMap = map
        this.googleMap?.run {
            setOnMapClickListener(this@MainActivity)
            setOnInfoWindowClickListener(this@MainActivity)
            launchAndCollect(viewModel.state){
                it.error?.let {
                    Toast.makeText(this@MainActivity,this@MainActivity.errorToString(it),Toast.LENGTH_SHORT).show()
                }

                it.monumentos?.let {list ->
                    if (list.isNotEmpty()) {
                        list.first().geometry?.let {
                            val poiLocation = LatLng(it.coordinates[1], it.coordinates[0])
                            moveCamera(CameraUpdateFactory.newLatLngZoom(poiLocation, 12f))
                        }
                    }
                    list.forEach {monumento ->
                        monumento.geometry?.let {
                            val poiLocation = LatLng(it.coordinates[1], it.coordinates[0])
                            addCustomMarker(monumento.id,poiLocation,monumento.title)
                            addMarker(MarkerOptions().position(poiLocation).title(monumento.title))
                        }
                    }
                }
            }
        }

    }

    override fun onMapClick(location: LatLng) {
        googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12f))
    }

    override fun onInfoWindowClick(marker: Marker) {
        val id = markersMap.filterValues { it == marker }.keys.firstOrNull() ?: 0
        markersMap.clear()
        googleMap?.clear()
        goToDetail(id)

    }

}