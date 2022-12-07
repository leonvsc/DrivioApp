package nl.avans.drivioapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import nl.avans.drivioapp.adapter.AdvertisementAdapter
import nl.avans.drivioapp.databinding.FragmentDiscoverBinding
import nl.avans.drivioapp.viewModel.AdvertisementViewModel

class DiscoverFragment : Fragment(R.layout.fragment_discover) {
    private var _binding: FragmentDiscoverBinding? = null;
    private val binding get() = _binding!!;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDiscoverBinding.inflate(inflater, container, false);
        val view = binding.root;
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val advertisementViewModel: AdvertisementViewModel by viewModels()
        advertisementViewModel.advertisementResponse.observe(viewLifecycleOwner) {
            val obj = advertisementViewModel.advertisementResponse.value
            val recyclerView = binding.recyclerView
            recyclerView.adapter = AdvertisementAdapter(this, obj)

////TODO: Show everything in a recyclerview. At this moment the for loop override every textview. It needs to add multiple items of advertisement.
//
//            for (i in 0 until obj.length()) {
//                val advertisement: JSONObject = obj.getJSONObject(i)
//                val advertisementTitle = advertisement.getString("title")
//                val advertisementDescription = advertisement.getString("description")
//                val advertisementPrice = advertisement.getString("price")
//                val advertisementStartDate = advertisement.getString("startDate")
//                val advertisementEndDate = advertisement.getString("endDate")
//
//                binding.tvTitle.text = advertisementTitle
//                binding.tvDescription.text = advertisementDescription
//                binding.tvPrice.text = advertisementPrice
//                binding.tvStartDate.text = advertisementStartDate
//                binding.tvEndDate.text = advertisementEndDate
//            }

        }

        binding.getBtn.setOnClickListener {
            advertisementViewModel.getAdvertisements()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}