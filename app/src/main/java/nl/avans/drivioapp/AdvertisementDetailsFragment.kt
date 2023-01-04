package nl.avans.drivioapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import nl.avans.drivioapp.databinding.FragmentAdvertisementDetailsBinding
import nl.avans.drivioapp.model.Advertisement
import nl.avans.drivioapp.model.Reservation
import nl.avans.drivioapp.model.User
import nl.avans.drivioapp.viewModel.AdvertisementViewModel
import nl.avans.drivioapp.viewModel.ReservationViewModel
import retrofit2.Response

class AdvertisementDetailsFragment : Fragment(R.layout.fragment_advertisement_details) {

    private var _binding: FragmentAdvertisementDetailsBinding? = null;
    private val binding get() = _binding!!;
    private val advertisementViewModel: AdvertisementViewModel by viewModels()
    private val reservationViewModel: ReservationViewModel by viewModels()
    private lateinit var advertisement: Response<Advertisement>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdvertisementDetailsBinding.inflate(inflater, container, false);
        val view = binding.root;
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvTitle: TextView = binding.tvTitle
        val tvDescription: TextView = binding.tvDescription
        val tvPrice: TextView = binding.tvPrice
        val tvStartDate: TextView = binding.tvStartDate
        val tvEndDate: TextView = binding.tvEndDate

        setFragmentResultListener("advertisementId") { requestKey, bundle ->
            val advertisementId = bundle.getInt("advertisementId")
            advertisementViewModel.getAdvertisementById(advertisementId)

            val ibtnRemove = binding.ibtnRemove

            ibtnRemove.setOnClickListener {
                advertisementViewModel.deleteAdvertisementWithResponse(advertisementId)
            }
        }

        advertisementViewModel.deleteAdvertisementResponse.observe(viewLifecycleOwner) {
            val response = advertisementViewModel.deleteAdvertisementResponse.value


            if (response?.code() == 200) {
                Toast.makeText(activity, "Delete successful!!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity, "Deletion failed!!", Toast.LENGTH_SHORT).show()
            }
            setFragmentResult(
                "advertisementIdEdit",
                bundleOf("advertisementIdEdit" to advertisementId)
            )
        }

        val ibtnEdit = binding.ibtnEdit
        val ibtnRemove = binding.ibtnRemove

        ibtnEdit.setOnClickListener {
            replaceFragment(EditAdvertisement())
        }

        advertisementViewModel.getAdvertisementByIdResponse.observe(viewLifecycleOwner) {
            advertisement = advertisementViewModel.getAdvertisementByIdResponse.value!!

            tvTitle.text = advertisement.body()?.title.toString()
            tvDescription.text = advertisement.body()?.description.toString()
            tvPrice.text = advertisement.body()?.price.toString()
            tvStartDate.text = advertisement.body()?.startDate.toString()
            tvEndDate.text = advertisement.body()?.endDate.toString()

            if (advertisement.body()?.user?.userId == 47) {
                binding.ibtnEdit.isVisible = true
                binding.ibtnRemove.isVisible = true
            } else {
                binding.btnReserve.isVisible = true
            }
        }

        binding.btnReserve.setOnClickListener {
            val advertisementId = advertisement.body()?.advertisementId
            val reservation = Reservation(
                null,
                advertisement.body()?.startDate.toString(),
                advertisement.body()?.endDate.toString(),
                true,
                User(29),
                Advertisement(advertisementId)
            )

            reservationViewModel.postReservationWithResponse(reservation)

            reservationViewModel.postReservationResponse.observe(viewLifecycleOwner) {
                val response = reservationViewModel.postReservationResponse.value


                if (response?.code() == 200) {
                    Toast.makeText(activity, "Success!!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(activity, "Failed!!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.flFragment, fragment)
        fragmentTransaction?.commit()
    }
}