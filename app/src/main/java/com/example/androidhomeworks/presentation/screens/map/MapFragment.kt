package com.example.androidhomeworks.presentation.screens.map

import LocationRenderer
import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.androidhomeworks.R
import com.example.androidhomeworks.common.Resource
import com.example.androidhomeworks.data.remote.location.LocationDTO
import com.example.androidhomeworks.databinding.FragmentMapBinding
import com.example.androidhomeworks.presentation.base_framgent.BaseFragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import com.google.maps.android.clustering.ClusterManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MapFragment : BaseFragment<FragmentMapBinding>(FragmentMapBinding::inflate),
    OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap
    private val mapViewModel: MapViewModel by viewModels()

    private lateinit var clusterManager: ClusterManager<LocationDTO>

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        requestPermissions()
    }

    override fun setUp() {
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        mapViewModel.getLocations()
        mapState()
        listeners()
    }

    private fun requestPermissions() {
        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) ||
                        permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    checkLocationEnabled()
                }
                else -> {
                    Toast.makeText(requireContext(),
                        getString(R.string.location_permission_is_required), Toast.LENGTH_LONG).show()
                    requireActivity().finish()
                }
            }
        }

        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    private fun checkLocationEnabled() {
        if (!isLocationEnabled(requireContext())) {
            Toast.makeText(requireContext(),
                getString(R.string.please_enable_location_services), Toast.LENGTH_LONG).show()
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(intent)
        }
    }

    private fun isLocationEnabled(context: Context): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            locationManager.isLocationEnabled
        } else {
            val gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            val networkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
            gpsEnabled || networkEnabled
        }
    }

    private fun mapState() {
        lifecycleScope.launch {
            mapViewModel.locations.collect { resource ->
                when (resource) {
                    is Resource.Loading -> loading()
                    is Resource.Success -> {
                        loaded()
                        updateMapWithLocations(resource.data)
                    }
                    is Resource.Error -> {
                        loaded()
                        Snackbar.make(binding.root, resource.errorMessage, Snackbar.LENGTH_LONG)
                            .show()
                    }
                }
            }
        }
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        clusterManager = ClusterManager(requireContext(), googleMap)
        clusterManager.renderer = LocationRenderer(requireContext(), googleMap, clusterManager)

        googleMap.setOnCameraIdleListener(clusterManager)
        clusterManager.setOnClusterItemClickListener { locationDto ->
            findNavController().navigate(
                MapFragmentDirections.actionMapFragmentToMarkerBottomSheetFragment(locationDto)
            )
            true
        }


        enableMyLocation()
        getDeviceLocation()
    }

    private fun enableMyLocation() {
        if (requireContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
            requireContext().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            googleMap.isMyLocationEnabled = true
        }
    }

    private fun getDeviceLocation() {
        try {
            if (requireContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                requireContext().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                fusedLocationClient.getCurrentLocation(PRIORITY_HIGH_ACCURACY, null)
                    .addOnSuccessListener { location: Location? ->
                        if (location != null) {
                            val latLng = LatLng(location.latitude, location.longitude)
                            googleMap.addMarker(
                                MarkerOptions()
                                    .position(latLng)
                                    .title(getString(R.string.your_location))
                                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                            )
                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
                        } else {
                            Snackbar.make(binding.root,
                                getString(R.string.unable_to_get_device_location), Snackbar.LENGTH_LONG).show()
                        }
                    }
                    .addOnFailureListener {
                        Snackbar.make(binding.root,
                            getString(R.string.failed_to_get_location), Snackbar.LENGTH_LONG).show()
                    }
            }
        } catch (e: SecurityException) {
            Snackbar.make(binding.root,
                getString(R.string.location_permissions_required), Snackbar.LENGTH_LONG).show()
        }
    }


    private fun zoomIn() {
        val currentZoomLevel = googleMap.cameraPosition.zoom
        if (currentZoomLevel < googleMap.maxZoomLevel) {
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(currentZoomLevel + 1))
        }
    }

    private fun zoomOut() {
        val currentZoomLevel = googleMap.cameraPosition.zoom
        if (currentZoomLevel > googleMap.minZoomLevel) {
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(currentZoomLevel - 1))
        }
    }

    private fun updateMapWithLocations(locations: List<LocationDTO>) {
        clusterManager.clearItems()
        clusterManager.addItems(locations)
        clusterManager.cluster()
    }


    private fun listeners() {
        binding.zoomInButton.setOnClickListener { zoomIn() }
        binding.zoomOutButton.setOnClickListener { zoomOut() }
    }

    private fun loading() {
        binding.loading.visibility = View.VISIBLE
    }

    private fun loaded() {
        binding.loading.visibility = View.GONE
    }
}
