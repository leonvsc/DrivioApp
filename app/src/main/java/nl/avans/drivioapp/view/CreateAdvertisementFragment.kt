package nl.avans.drivioapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.datepicker.MaterialDatePicker
import nl.avans.drivioapp.databinding.FragmentCreateAdvertisementBinding
import nl.avans.drivioapp.model.Advertisement
import nl.avans.drivioapp.model.ElectricCar
import nl.avans.drivioapp.model.User1
import nl.avans.drivioapp.viewModel.AdvertisementViewModel
import nl.avans.drivioapp.viewModel.MyCarsViewModel
import java.text.SimpleDateFormat
import java.util.*


class CreateAdvertisementFragment : Fragment(R.layout.fragment_create_advertisement) {
    private var _binding: FragmentCreateAdvertisementBinding? = null;
    private val binding get() = _binding!!;
    private val advertisementViewModel: AdvertisementViewModel by viewModels()
    private val myCarsViewModel: MyCarsViewModel by viewModels()
    private lateinit var startDate: String
    private lateinit var endDate: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateAdvertisementBinding.inflate(inflater, container, false);
        val view = binding.root;
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dateRangePicker =
            MaterialDatePicker.Builder.dateRangePicker()
                .setTitleText("Select dates")
                .build()

        binding.btnSelectDate.setOnClickListener {
            // Show Date Range Picker
            activity?.let { it1 -> dateRangePicker.show(it1.supportFragmentManager, "DATE_PICKER") }
        }

        dateRangePicker.addOnPositiveButtonClickListener {
            val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            startDate = dateFormatter.format(it.first)
            endDate = dateFormatter.format(it.second)

            binding.tvStartDate.text = "Startdate: $startDate"
            binding.tvEndDate.text = "Enddate: $endDate"
        }

        val carsList = arrayListOf<String?>()

        myCarsViewModel.electricCarResponse.observe(viewLifecycleOwner) {
            // Add car location markers on the map
            var myCars = myCarsViewModel.electricCarResponse.value!!
            myCars = myCars.filter { it.user?.userId == 47 }
            for (myCar in myCars) {
                carsList.add(myCar.carId.toString())
            }

            val spinner: Spinner = binding.spinnerLanguages

            val adapter: ArrayAdapter<String> =
                ArrayAdapter<String>(
                    this.requireContext(),
                    android.R.layout.simple_spinner_item,
                    carsList
                )


            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            spinner.adapter = adapter;

            binding.btnConfirm.setOnClickListener {
                val title = binding.etTitle.text.toString()
                val description = binding.etDescription.text.toString()
                val price = binding.etPrice.text.toString().toDouble()
                val startDate = startDate
                val endDate = endDate
                val carId = 55

                val advertisement = Advertisement(
                    null,
                    title,
                    description,
                    price,
                    startDate,
                    endDate,
                    User1(47),
                    ElectricCar(carId)
                )
                advertisementViewModel.postAdvertisementWithResponse(advertisement)

                advertisementViewModel.postAdvertisementResponse.observe(viewLifecycleOwner) {
                    val response = advertisementViewModel.postAdvertisementResponse.value

                    if (response?.code() == 200) {
                        Toast.makeText(activity, "Success!!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(activity, "Failed!!", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }
    }
}