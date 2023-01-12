package nl.avans.drivioapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.google.android.material.datepicker.MaterialDatePicker
import nl.avans.drivioapp.databinding.FragmentEditBookingBinding
import nl.avans.drivioapp.model.Advertisement
import nl.avans.drivioapp.model.Reservation
import nl.avans.drivioapp.model.User
import nl.avans.drivioapp.model.User1
import nl.avans.drivioapp.viewModel.ReservationViewModel
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class EditBooking : Fragment(R.layout.fragment_edit_booking) {
    private var _binding: FragmentEditBookingBinding? = null;
    private val binding get() = _binding!!;
    private val reservationViewModel: ReservationViewModel by viewModels()
    private lateinit var reservation: Response<Reservation>
    private var reservationId: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditBookingBinding.inflate(inflater, container, false);
        val view = binding.root;
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener("reservationIdEdit") { requestKey, bundle ->
            reservationId = bundle.getInt("reservationIdEdit")
            reservationViewModel.getReservationById(reservationId)
        }

        reservationViewModel.getReservationByIdResponse.observe(viewLifecycleOwner) {
            reservation = reservationViewModel.getReservationByIdResponse.value!!

            val tvTitle = binding.tvTitle
            val etStartDate = binding.etStartDate
            val etEndDate = binding.etEndDate

            tvTitle.text = reservation.body()?.advertisement?.title.toString()
            etStartDate.setText(reservation.body()?.startDate)
            etEndDate.setText(reservation.body()?.endDate)

            val startDatePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select date")
                    .build()

            val endDatePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select date")
                    .build()

            etStartDate.setOnClickListener {
                activity?.let { it1 ->
                    startDatePicker.show(
                        it1.supportFragmentManager,
                        "DATE_PICKER"
                    )
                }
            }

            etEndDate.setOnClickListener {
                activity?.let { it1 ->
                    endDatePicker.show(
                        it1.supportFragmentManager,
                        "DATE_PICKER"
                    )
                }
            }

            startDatePicker.addOnPositiveButtonClickListener {
                val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val startDate = dateFormatter.format(it)
                binding.etStartDate.setText(startDate)
            }

            endDatePicker.addOnPositiveButtonClickListener {
                val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val endDate = dateFormatter.format(it)
                binding.etEndDate.setText(endDate)
            }

            binding.btnConfirm.setOnClickListener {
                val startDate = binding.etStartDate.text.toString()
                val endDate = binding.etEndDate.text.toString()

                val reservationPut = Reservation(
                    reservationId,
                    startDate,
                    endDate,
                    true,
                    User1(47),
                    Advertisement(reservation.body()?.advertisement?.advertisementId)
                )
                reservationViewModel.putReservationWithResponse(reservationPut)
            }

            reservationViewModel.putReservationResponse.observe(viewLifecycleOwner) {
                val response = reservationViewModel.putReservationResponse.value

                if (response?.code() == 200) {
                    Toast.makeText(activity, "Success!!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(activity, "Failed!!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}