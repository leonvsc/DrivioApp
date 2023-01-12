package nl.avans.drivioapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import nl.avans.drivioapp.R
import nl.avans.drivioapp.adapter.AdvertisementAdapter

import nl.avans.drivioapp.databinding.FragmentDiscoverBinding
import nl.avans.drivioapp.model.Advertisement
import nl.avans.drivioapp.viewModel.AdvertisementViewModel

class DiscoverFragment : Fragment(R.layout.fragment_discover),
    AdvertisementAdapter.OnItemClickListener {

    private var _binding: FragmentDiscoverBinding? = null;
    private val binding get() = _binding!!;
    private val advertisementViewModel: AdvertisementViewModel by viewModels()
    private lateinit var advertisement: List<Advertisement>

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

        advertisementViewModel.getAdvertisementResponse.observe(viewLifecycleOwner) {
            advertisement = advertisementViewModel.getAdvertisementResponse.value!!
            val recyclerView = binding.recyclerView
            recyclerView.adapter = AdvertisementAdapter(advertisement, this)
        }

        val swipeRefreshLayout = binding.root
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            advertisementViewModel.getAdvertisements()
        }
    }

    override fun onItemClick(position: Int) {
        advertisementViewModel.getAdvertisementResponse.observe(viewLifecycleOwner) {
            setFragmentResult(
                "advertisementId",
                bundleOf("advertisementId" to advertisement[position].advertisementId)
            )
        }
        findNavController().navigate(R.id.action_discoverFragment_to_advertisementDetailsFragment)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

