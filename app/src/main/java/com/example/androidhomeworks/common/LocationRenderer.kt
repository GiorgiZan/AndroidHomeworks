import android.content.Context
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.example.androidhomeworks.data.remote.location.LocationDTO
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import com.example.androidhomeworks.R
import com.google.codelabs.buildyourfirstmap.BitmapHelper

class LocationRenderer(
    private val context: Context,
    map: GoogleMap,
    clusterManager: ClusterManager<LocationDTO>
) : DefaultClusterRenderer<LocationDTO>(context, map, clusterManager) {

    /**
     * The icon to use for each cluster item
     */
    private val locationIcon: BitmapDescriptor by lazy {
        BitmapHelper.vectorToBitmap(context, R.drawable.pin)
    }

    /**
     * Method called before the cluster item (the marker) is rendered.
     * This is where marker options should be set.
     */
    override fun onBeforeClusterItemRendered(item: LocationDTO, markerOptions: MarkerOptions) {
        markerOptions.title(item.title)
            .position(item.position)  // Use getPosition() from LocationDTO
            .icon(locationIcon)  // Use the custom icon
    }

    /**
     * Method called right after the cluster item (the marker) is rendered.
     * This is where properties for the Marker object should be set.
     */
    override fun onClusterItemRendered(clusterItem: LocationDTO, marker: Marker) {
        marker.tag = clusterItem  // Store the LocationDTO in the marker's tag
    }
}
