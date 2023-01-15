package nl.avans.drivioapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.navigation.fragment.findNavController
import nl.avans.drivioapp.R
import nl.avans.drivioapp.databinding.FragmentLoggedInBinding

class LoggedInFragment : Fragment(R.layout.fragment_logged_in) {
    private var _binding: FragmentLoggedInBinding? = null;
    private val binding get() = _binding!!;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoggedInBinding.inflate(inflater, container, false);
        val view = binding.root;
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.logout.setOnClickListener {
            findNavController().navigate(R.id.action_loggedInFragment_to_profileFragment)
            parentFragmentManager.commit {
                replace<ProfileFragment>(R.id.fragmentContainerView)
                setReorderingAllowed(true)
                addToBackStack(null)
            }
        }

        binding.buttonViewMyCars.setOnClickListener{
            findNavController().navigate(R.id.action_loggedInFragment_to_myCarsFragment)
        }
        binding.btnAdvertisement.setOnClickListener {
            findNavController().navigate(R.id.action_loggedInFragment_to_advertisementProfileFragment)
        }
        binding.buttonViewStatistics.setOnClickListener {
            findNavController().navigate(R.id.action_loggedInFragment_to_statisticsFragment)
        }
    }
}
