package nl.avans.drivioapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import nl.avans.drivioapp.databinding.FragmentAdvertisementDetailsBinding
import nl.avans.drivioapp.databinding.FragmentDiscoverBinding
import nl.avans.drivioapp.viewModel.AdvertisementViewModel
import org.w3c.dom.Text

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

    setFragmentResultListener("requestKey") { requestKey, bundle ->
        val advertisementId = bundle.getInt("advertisementId")
        val tvTitle: TextView = binding.tvTitle
        val tvDescription: TextView = binding.tvDescription
        val tvPrice: TextView = binding.tvPrice
        val tvStartDate: TextView = binding.tvStartDate
        val tvEndDate: TextView = binding.tvEndDate

        advertisementViewModel.getAdvertisementByIdResponse.observe(viewLifecycleOwner) {
            val advertisement = advertisementViewModel.getAdvertisementByIdResponse.value
            val advertisement2 = advertisementViewModel.getAdvertisementById(advertisementId)
        }

//        textView.text = result.toString()

    }

        return view;
    }

}