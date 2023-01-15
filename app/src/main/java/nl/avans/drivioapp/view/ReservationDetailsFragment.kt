package nl.avans.drivioapp.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import nl.avans.drivioapp.R
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

            val ibtnRemove = binding.ibtnRemove

            ibtnRemove.setOnClickListener {
                reservationViewModel.deleteReservationWithResponse(reservationId)
            }

            setFragmentResult(
                "reservationIdEdit",
                bundleOf("reservationIdEdit" to reservationId)
            )
        }

        reservationViewModel.deleteReservationResponse.observe(viewLifecycleOwner) {
            val response = reservationViewModel.deleteReservationResponse.value


            if (response?.code() == 200) {
                Toast.makeText(activity, "Delete successful!!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity, "Deletion failed!!", Toast.LENGTH_SHORT).show()
            }
        }

        reservationViewModel.getReservationByIdResponse.observe(viewLifecycleOwner) {
            reservation = reservationViewModel.getReservationByIdResponse.value!!

            tvTitle.text = reservation.body()?.advertisement?.title.toString()
            tvStartDate.text = reservation.body()?.startDate
            tvEndDate.text = reservation.body()?.endDate

            val btnNavigate = binding.btnNavigate
            val carLatitude = reservation.body()?.advertisement?.electricCar?.latitude
            val carLongitude = reservation.body()?.advertisement?.electricCar?.longitude
            val uri =
                Uri.parse("https://www.google.com/maps/dir/?api=1&destination=${carLatitude},${carLongitude}")
            val mapIntent = Intent(Intent.ACTION_VIEW, uri)

            btnNavigate.setOnClickListener {
                startActivity(mapIntent)
            }
        }

        val ibtnEdit = binding.ibtnEdit

        ibtnEdit.setOnClickListener {
            findNavController().navigate(R.id.action_reservationDetailsFragment_to_editBooking)
        }
    }
}