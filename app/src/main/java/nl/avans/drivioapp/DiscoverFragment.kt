package nl.avans.drivioapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import nl.avans.drivioapp.adapter.AdvertisementAdapter
import nl.avans.drivioapp.databinding.FragmentDiscoverBinding
import nl.avans.drivioapp.viewModel.AdvertisementViewModel

class DiscoverFragment : Fragment(R.layout.fragment_discover), AdvertisementAdapter.OnItemClickListener {
    private var _binding: FragmentDiscoverBinding? = null;
    private val binding get() = _binding!!;
    private val advertisementViewModel: AdvertisementViewModel by viewModels()

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

        // TODO: Opschonen en versimpelen met een directe call naar de api: advertisementViewModel.getAdvertisements()
        advertisementViewModel.getAdvertisementResponse.observe(viewLifecycleOwner) {
            val obj = advertisementViewModel.getAdvertisementResponse.value ?: listOf()
            val recyclerView = binding.recyclerView
            recyclerView.adapter = AdvertisementAdapter(this, obj, this)
        }

        binding.getBtn.setOnClickListener {
            advertisementViewModel.getAdvertisements()
        }
    }

    override fun onItemClick(position: Int) {

        // TODO: Opschonen en versimpelen met een directe call naar de api: advertisementViewModel.getAdvertisementById()
        advertisementViewModel.getAdvertisementResponse.observe(viewLifecycleOwner) {
            val obj = advertisementViewModel.getAdvertisementResponse.value ?: listOf()
            setFragmentResult("advertisementId", bundleOf("advertisementId" to obj[position].advertisementId))
            setFragmentResult("title", bundleOf("title" to obj[position].title))
            setFragmentResult("description", bundleOf("description" to obj[position].description))
            setFragmentResult("price", bundleOf("price" to obj[position].price))
            setFragmentResult("startDate", bundleOf("startDate" to obj[position].startDate))
            setFragmentResult("endDate", bundleOf("endDate" to obj[position].endDate))
        }
        replaceFragment(AdvertisementDetailsFragment())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.flFragment, fragment)
        fragmentTransaction?.commit()
    }

}