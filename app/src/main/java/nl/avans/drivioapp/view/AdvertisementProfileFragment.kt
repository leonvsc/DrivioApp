package nl.avans.drivioapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import nl.avans.drivioapp.R
import nl.avans.drivioapp.adapter.AdvertisementAdapter
import nl.avans.drivioapp.databinding.FragmentAdvertisementProfileBinding
import nl.avans.drivioapp.model.Advertisement
import nl.avans.drivioapp.viewModel.AdvertisementViewModel

class AdvertisementProfileFragment : Fragment(R.layout.fragment_advertisement_profile),
    AdvertisementAdapter.OnItemClickListener {
    private var _binding: FragmentAdvertisementProfileBinding? = null;
    private val binding get() = _binding!!;
    private val advertisementViewModel: AdvertisementViewModel by viewModels()
    private lateinit var advertisement: List<Advertisement>
    private lateinit var advertisementByUser: List<Advertisement>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdvertisementProfileBinding.inflate(inflater, container, false);
        val view = binding.root;
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        advertisementViewModel.getAdvertisementResponse.observe(viewLifecycleOwner) {
            advertisement = advertisementViewModel.getAdvertisementResponse.value!!
            advertisementByUser = advertisement.filter { it.user?.userId == 47 }
            val recyclerView = binding.recyclerView
            recyclerView.adapter = AdvertisementAdapter(advertisementByUser, this)
        }

        val swipeRefreshLayout = binding.swipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            advertisementViewModel.getAdvertisements()
        }

        binding.btnCreateAdvertisement.setOnClickListener {
            findNavController().navigate(R.id.action_advertisementProfileFragment_to_createAdvertisementFragment)
        }
    }

    override fun onItemClick(position: Int) {
        advertisementViewModel.getAdvertisementResponse.observe(viewLifecycleOwner) {
            // Send advertisementId
            setFragmentResult(
                "advertisementId",
                bundleOf("advertisementId" to advertisementByUser[position].advertisementId)
            )
        }
        findNavController().navigate(R.id.action_advertisementProfileFragment_to_advertisementDetailsFragment)
    }
}
