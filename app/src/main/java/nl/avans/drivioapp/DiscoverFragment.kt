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
            val obj = advertisementViewModel.advertisementResponse.value ?: listOf()
            val recyclerView = binding.recyclerView
            recyclerView.adapter = AdvertisementAdapter(this, obj)
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