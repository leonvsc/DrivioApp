package nl.avans.drivioapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import nl.avans.drivioapp.adapter.ReservationAdapter
import nl.avans.drivioapp.databinding.FragmentBookingsBinding
import nl.avans.drivioapp.model.Reservation
import nl.avans.drivioapp.viewModel.ReservationViewModel

class BookingsFragment : Fragment(R.layout.fragment_bookings),
    ReservationAdapter.OnItemClickListener {
    private var _binding: FragmentBookingsBinding? = null;
    private val binding get() = _binding!!;
    private val reservationViewModel: ReservationViewModel by viewModels()
    private lateinit var reservation: List<Reservation>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookingsBinding.inflate(inflater, container, false);
        val view = binding.root;
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        reservationViewModel.getReservationsResponse.observe(viewLifecycleOwner) {
            reservation = reservationViewModel.getReservationsResponse.value!!
            val recyclerView = binding.recyclerView
            recyclerView.adapter = ReservationAdapter(reservation, this)
        }

        val swipeRefreshLayout = binding.swipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            reservationViewModel.getReservations()
        }

    }

    override fun onItemClick(position: Int) {
        reservationViewModel.getReservationsResponse.observe(viewLifecycleOwner) {
            setFragmentResult(
                "reservationId",
                bundleOf("reservationId" to reservation[position].reservationId)
            )
        }
    }
}