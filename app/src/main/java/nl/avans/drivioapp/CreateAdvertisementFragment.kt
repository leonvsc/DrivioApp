package nl.avans.drivioapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.datepicker.MaterialDatePicker
import nl.avans.drivioapp.databinding.FragmentCreateAdvertisementBinding
import nl.avans.drivioapp.model.Advertisement
import nl.avans.drivioapp.model.User
import nl.avans.drivioapp.viewModel.AdvertisementViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class CreateAdvertisementFragment : Fragment(R.layout.fragment_create_advertisement) {
    private var _binding: FragmentCreateAdvertisementBinding? = null;
    private val binding get() = _binding!!;
    private val advertisementViewModel: AdvertisementViewModel by viewModels()
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

        val advertisementViewModel: AdvertisementViewModel by viewModels()
        advertisementViewModel.postAdvertisementResponse.observe(viewLifecycleOwner) {
            Toast.makeText(context, "Advertisement created with response: ${advertisementViewModel.postAdvertisementResponse.value}", Toast.LENGTH_SHORT).show()
        }

        val dateRangePicker =
            MaterialDatePicker.Builder.dateRangePicker()
                .setTitleText("Select date")
                .build()

        binding.btnSelectDate.setOnClickListener {
            activity?.let { it1 -> dateRangePicker.show(it1.supportFragmentManager, "DATE_PICKER") }
        }

        dateRangePicker.addOnPositiveButtonClickListener {
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            startDate = sdf.format(it.first)
            endDate = sdf.format(it.second)
//            startDate = LocalDate.parse(formattedStartDate)
//            endDate = LocalDate.parse(formattedEndDate)
//            val startDate = sdf.format(it.first)
//            val endDate = sdf.format(it.second)

            // TODO: Fix Resource string
            binding.tvStartDate.text = "Startdate: $startDate"
            binding.tvEndDate.text = "Enddate: $endDate"
        }

        binding.btnConfirm.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val description = binding.etDescription.text.toString()
            val price = binding.etPrice.text.toString().toDouble()
            val startDate = startDate
            val endDate = endDate

            val advertisement = Advertisement(
                null,
                title,
                description,
                price,
                startDate,
                endDate,
                User(29)
            )

            advertisementViewModel.postAdvertisementWithResponse(advertisement)


            // TODO: Navigate to something when the post request was successful
//            replaceFragment(AdvertisementProfileFragment())
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.flFragment, fragment)
        fragmentTransaction?.commit()
    }

}