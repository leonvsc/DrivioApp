package nl.avans.drivioapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import nl.avans.drivioapp.databinding.FragmentAdvertisementProfileBinding
import nl.avans.drivioapp.databinding.FragmentDiscoverBinding

class AdvertisementProfileFragment : Fragment(R.layout.fragment_advertisement_profile) {
    private var _binding: FragmentAdvertisementProfileBinding? = null;
    private val binding get() = _binding!!;

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

        binding.btnCreateAdvertisement.setOnClickListener {
            replaceFragment(CreateAdvertisementFragment())
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.flFragment, fragment)
        fragmentTransaction?.commit()
    }
}
