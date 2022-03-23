package com.example.test_kotlin.fragments

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.test_kotlin.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*


class MapsFragment : Fragment() {
    //    private val args: MapsFragmentArgs by navArgs()
    private val callback = OnMapReadyCallback { googleMap ->

        val sydney = LatLng(21.7398, 70.4491)
//        val sydney = LatLng(args.latitude.toDouble(), args.longitude.toDouble())
        val geocoder = Geocoder(requireContext(), Locale.getDefault())
        val addresses: List<Address> =
            geocoder.getFromLocation(sydney.latitude, sydney.longitude, 1)
        val cityName: String = addresses[0].getAddressLine(0)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in $cityName"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 18F))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}