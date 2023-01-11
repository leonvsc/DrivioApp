package nl.avans.drivioapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import nl.avans.drivioapp.viewModel.AddElectricCarViewModel

class MapFragment : Fragment(R.layout.fragment_map), OnMapReadyCallback {
    private lateinit var map: GoogleMap
    private val addElectricCarViewModel: AddElectricCarViewModel by viewModels()
    private val LOCATION_PERMISSION_REQUEST = 1

    private val carLocation = mutableListOf<LatLng>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        addMarkers()
        getLocationAccess()
        val amsterdamBounds = LatLngBounds(
            LatLng((52.251333), 4.654195),  // SW bounds
            LatLng((52.464782), 5.086095) // NE bounds
        )
        map.moveCamera(CameraUpdateFactory.newLatLngBounds(amsterdamBounds, 0))
    }

    private fun addMarkers() {

        addElectricCarViewModel.electricCarResponse.observe(viewLifecycleOwner) {
            val electricCars = addElectricCarViewModel.electricCarResponse.value!!
            for (electricCar in electricCars) {
                carLocation.add(LatLng(electricCar.latitude, electricCar.longitude))
            }

            carLocation.forEachIndexed {index, carLocation ->
                val marker = map.addMarker(
                    MarkerOptions()
                        .title(electricCars[index].brand + " " + electricCars[index].model)
                        .position(carLocation)
                )
                marker?.tag = carLocation
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocationAccess() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            map.isMyLocationEnabled = true
        } else ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_PERMISSION_REQUEST
        )
    }
}