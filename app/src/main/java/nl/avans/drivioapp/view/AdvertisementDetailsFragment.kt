package nl.avans.drivioapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import nl.avans.drivioapp.R
import nl.avans.drivioapp.databinding.FragmentAdvertisementDetailsBinding
import nl.avans.drivioapp.viewModel.AdvertisementViewModel

class AdvertisementDetailsFragment : Fragment(R.layout.fragment_advertisement_details) {

    private var _binding: FragmentAdvertisementDetailsBinding? = null;
    private val binding get() = _binding!!;
    private val advertisementViewModel: AdvertisementViewModel by viewModels()

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
        }

        advertisementViewModel.getAdvertisementByIdResponse.observe(viewLifecycleOwner) {
            val advertisement = advertisementViewModel.getAdvertisementByIdResponse.value

            tvTitle.text = advertisement?.body()?.title.toString()
            tvDescription.text = advertisement?.body()?.description.toString()
            tvPrice.text = advertisement?.body()?.price.toString()
            tvStartDate.text = advertisement?.body()?.startDate.toString()
            tvEndDate.text = advertisement?.body()?.endDate.toString()
        }
    }
}