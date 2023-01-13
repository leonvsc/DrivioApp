package nl.avans.drivioapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
        binding.buttonAddElectricCar.setOnClickListener{
            findNavController().navigate(R.id.action_loggedInFragment_to_addElectricCarFragment)
        }
        binding.buttonAddFuelCar.setOnClickListener{
            findNavController().navigate(R.id.action_loggedInFragment_to_addFuelCarFragment)
        }
        binding.buttonAddHydrogenCar.setOnClickListener{
            findNavController().navigate(R.id.action_loggedInFragment_to_addHydrogenCarFragment)
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