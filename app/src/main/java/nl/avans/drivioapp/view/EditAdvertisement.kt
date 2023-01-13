package nl.avans.drivioapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.google.android.material.datepicker.MaterialDatePicker
import nl.avans.drivioapp.R
import nl.avans.drivioapp.databinding.FragmentEditAdvertisementBinding
import nl.avans.drivioapp.model.Advertisement
import nl.avans.drivioapp.model.User1
import nl.avans.drivioapp.viewModel.AdvertisementViewModel
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class EditAdvertisement : Fragment(R.layout.fragment_edit_advertisement) {
    private var _binding: FragmentEditAdvertisementBinding? = null;
    private val binding get() = _binding!!;
    private val advertisementViewModel: AdvertisementViewModel by viewModels()
    private lateinit var advertisement: Response<Advertisement>
    private var advertisementId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditAdvertisementBinding.inflate(inflater, container, false);
        val view = binding.root;
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener("advertisementIdEdit") { requestKey, bundle ->
            advertisementId = bundle.getInt("advertisementIdEdit")
            advertisementViewModel.getAdvertisementById(advertisementId)
        }

        advertisementViewModel.getAdvertisementByIdResponse.observe(viewLifecycleOwner) {
            advertisement = advertisementViewModel.getAdvertisementByIdResponse.value!!

            val etTitle = binding.etTitle
            val etDescription = binding.etDescription
            val etPrice = binding.etPrice
            val etStartDate = binding.etStartDate
            val etEndDate = binding.etEndDate

            etTitle.setText(advertisement.body()?.title.toString())
            etDescription.setText(advertisement.body()?.description.toString())
            etPrice.setText(advertisement.body()?.price.toString())
            etStartDate.setText(advertisement.body()?.startDate.toString())
            etEndDate.setText(advertisement.body()?.endDate.toString())


            val startDatePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select date")
                    .build()

            val endDatePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select date")
                    .build()

            etStartDate.setOnClickListener {
                activity?.let { it1 -> startDatePicker.show(it1.supportFragmentManager, "DATE_PICKER") }
            }

            etEndDate.setOnClickListener {
                activity?.let { it1 -> endDatePicker.show(it1.supportFragmentManager, "DATE_PICKER") }
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
                val title = binding.etTitle.text.toString()
                val description = binding.etDescription.text.toString()
                val price = binding.etPrice.text.toString().toDouble()
                val startDate = binding.etStartDate.text.toString()
                val endDate = binding.etEndDate.text.toString()

                val advertisement = Advertisement(
                    advertisementId,
                    title,
                    description,
                    price,
                    startDate,
                    endDate,
                    User1(47)
                )
                advertisementViewModel.putAdvertisementWithResponse(advertisement)
            }

            advertisementViewModel.putAdvertisementResponse.observe(viewLifecycleOwner) {
                val response = advertisementViewModel.putAdvertisementResponse.value

                if (response?.code() == 200) {
                    Toast.makeText(activity, "Success!!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(activity, "Failed!!", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
}