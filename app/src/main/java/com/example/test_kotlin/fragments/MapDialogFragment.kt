package com.example.test_kotlin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.test_kotlin.databinding.MapViewDialogDemo4Binding
import com.google.android.gms.maps.GoogleMap

class MapDialogFragment : DialogFragment() {
    private lateinit var map: GoogleMap
    private lateinit var binding: MapViewDialogDemo4Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MapViewDialogDemo4Binding.inflate(layoutInflater)
        return binding.root
    }
}