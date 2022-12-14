package nl.avans.drivioapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import nl.avans.drivioapp.databinding.FragmentAddFuelCarBinding
import nl.avans.drivioapp.model.FuelCar
import nl.avans.drivioapp.model.User
import nl.avans.drivioapp.viewModel.AddFuelCarViewModel

class AddFuelCarFragment : Fragment(R.layout.fragment_add_fuel_car) {
    private val addFuelCarViewModel: AddFuelCarViewModel by viewModels();
    private var _binding: FragmentAddFuelCarBinding? = null;
    private val binding get() = _binding!!;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddFuelCarBinding.inflate(inflater, container, false);
        val view = binding.root;
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState);

        binding.postFuelCarBtn.setOnClickListener {

            val tankSize = binding.etTankSize.text.toString().toInt()
            val carRange = binding.etCarRange.text.toString().toInt()
            val literPer100Km = binding.etLiterPer100Km.text.toString().toDouble()
            val buildYear = binding.etBuildYear.text.toString().toInt()
            val numberPlate = binding.etNumberPlate.text.toString()
            val carType = binding.etCarType.text.toString()
            val fuelType = binding.etFuelType.text.toString()
            val model = binding.etModel.text.toString()
            val gearBox = binding.etGearBox.text.toString()
            val brand = binding.etBrand.text.toString()

            val fuelCar = FuelCar(
                carType,
                carRange,
                fuelType,
                literPer100Km,
                model,
                buildYear,
                gearBox,
                brand,
                numberPlate,
                tankSize,
                User(23)
            )
            addFuelCarViewModel.postFuelCar(fuelCar);
        }
    }

    override fun onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }
}