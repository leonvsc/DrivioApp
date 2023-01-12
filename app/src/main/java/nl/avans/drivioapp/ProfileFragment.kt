package nl.avans.drivioapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import nl.avans.drivioapp.databinding.FragmentAdvertisementProfileBinding
import nl.avans.drivioapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private var _binding: FragmentProfileBinding? = null;
    private val binding get() = _binding!!;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false);
        val view = binding.root;
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.buttonAddElectricCar.setOnClickListener{
//            replaceFragment(AddElectricCarFragment())
//        }
//        binding.buttonAddFuelCar.setOnClickListener{
//            replaceFragment(AddFuelCarFragment())
//        }
//        binding.buttonAddHydrogenCar.setOnClickListener{
//            replaceFragment(AddHydrogenCarFragment())
//        }
//        binding.buttonViewMyCars.setOnClickListener{
//            replaceFragment(MyCarsFragment())
//        }
//        binding.btnAdvertisement.setOnClickListener {
//            replaceFragment(AdvertisementProfileFragment())
//        }
//        binding.buttonViewStatistics.setOnClickListener {
//            replaceFragment(StatisticsFragment())
//        }
    }


//    private fun replaceFragment(fragment: Fragment) {
//        val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
//        fragmentTransaction?.replace(R.id.flFragment, fragment)
//        fragmentTransaction?.commit()
//    }
}