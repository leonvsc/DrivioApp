package nl.avans.drivioapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import nl.avans.drivioapp.databinding.FragmentMyCarDetailsBinding
import nl.avans.drivioapp.viewModel.MyCarsViewModel

class MyCarDetailsFragment : Fragment(R.layout.fragment_my_car_details){

    private var _binding: FragmentMyCarDetailsBinding? = null;
    private val binding get() = _binding!!;
    private val myCarsViewModel: MyCarsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyCarDetailsBinding.inflate(inflater, container, false);
        val view = binding.root;
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvCarId: TextView = binding.tvCarId
        val tvBrand: TextView = binding.tvBrand
        val tvBuildYear: TextView = binding.tvBuildYear
        val tvModel: TextView = binding.tvModel
        val tvFuelType: TextView = binding.tvFuelType

        setFragmentResultListener("carId") { requestKey, bundle ->
            val carId = bundle.getInt("carId")
            myCarsViewModel.getElectricCarById(carId)
        }

        myCarsViewModel.getElectricCarByIdResponse.observe(viewLifecycleOwner) {
            val myCar = myCarsViewModel.getElectricCarByIdResponse.value

            tvCarId.text = myCar?.body()?.carId.toString()
            tvBrand.text = myCar?.body()?.brand.toString()
            tvBuildYear.text = myCar?.body()?.buildYear.toString()
            tvModel.text = myCar?.body()?.model.toString()
            tvFuelType.text = myCar?.body()?.fuelType.toString()
        }
    }
}