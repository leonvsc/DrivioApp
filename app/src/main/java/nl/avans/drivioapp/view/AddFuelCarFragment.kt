package nl.avans.drivioapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import nl.avans.drivioapp.R
import nl.avans.drivioapp.databinding.FragmentAddFuelCarBinding
import nl.avans.drivioapp.model.FuelCar
import nl.avans.drivioapp.model.User1
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
                User1(23),
                0.0,
                0.0
            )
            addFuelCarViewModel.postFuelCarWithResponse(fuelCar);

            addFuelCarViewModel.postFuelCarResponse.observe(viewLifecycleOwner) {
                val response = addFuelCarViewModel.postFuelCarResponse.value

                if (response?.code() == 200) {
                    Toast.makeText(activity, "Success!!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(activity, "Failed!!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }
}