package com.example.androidhomeworks.data.remote.location

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationDTO(
    val lat: Double,
    val lan: Double,
    @SerialName("title")
    val markerTitle: String,
    val address: String
) : java.io.Serializable, ClusterItem {

    override fun getPosition(): LatLng {
        return LatLng(lat, lan)
    }

    override fun getTitle(): String {
        return markerTitle
    }

    override fun getSnippet(): String {
        return address
    }
}
