package nl.avans.drivioapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import nl.avans.drivioapp.databinding.FragmentReservationDetailsBinding
import nl.avans.drivioapp.model.Reservation
import nl.avans.drivioapp.viewModel.ReservationViewModel
import retrofit2.Response

class ReservationDetailsFragment : Fragment(R.layout.fragment_reservation_details) {
    private var _binding: FragmentReservationDetailsBinding? = null;
    private val binding get() = _binding!!;
    private val reservationViewModel: ReservationViewModel by viewModels()
    private lateinit var reservation: Response<Reservation>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReservationDetailsBinding.inflate(inflater, container, false);
        val view = binding.root;
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvTitle: TextView = binding.tvTitle
        val tvStartDate: TextView = binding.tvStartDate
        val tvEndDate: TextView = binding.tvEndDate

        setFragmentResultListener("reservationId") { requestKey, bundle ->
            val reservationId = bundle.getInt("reservationId")
            reservationViewModel.getReservationById(reservationId)
        }

        reservationViewModel.getReservationByIdResponse.observe(viewLifecycleOwner) {
            reservation = reservationViewModel.getReservationByIdResponse.value!!

            tvTitle.text = reservation.body()?.advertisement?.title.toString()
            tvStartDate.text = reservation.body()?.startDate
            tvEndDate.text = reservation.body()?.endDate
        }
    }
}