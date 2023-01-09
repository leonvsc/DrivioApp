package nl.avans.drivioapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment(R.layout.fragment_map), OnMapReadyCallback {
    private lateinit var map: GoogleMap

    private val carLocation = listOf(
        LatLng(38.5784, -121.4771),
        LatLng(38.5711, -121.4859),
        LatLng(38.561, -121.5017)
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        addMarkers()
    }

    private fun addMarkers() {
        carLocation.forEach { carLocation ->
            val marker = map.addMarker(
                MarkerOptions()
                    .position(carLocation)
            )
            marker?.tag = carLocation
        }
    }
}