package com.example.androidhomeworks.presentation.screens.bottom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.androidhomeworks.databinding.FragmentMarkerBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class MarkerBottomSheetFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentMarkerBottomSheetBinding? = null
    private val binding get() = _binding!!
    private val args: MarkerBottomSheetFragmentArgs  by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarkerBottomSheetBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        val locationDto = args.LocationDTO

        binding.tvPlaceTitle.text = locationDto.title
        binding.tvAddress.text = locationDto.address
        binding.tvLat.text = locationDto.lat.toString()
        binding.tvLan.text = locationDto.lan.toString()


    }
}