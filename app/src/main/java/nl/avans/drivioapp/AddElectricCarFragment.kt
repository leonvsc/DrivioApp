package nl.avans.drivioapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import nl.avans.drivioapp.databinding.FragmentAddElectricCarBinding
import nl.avans.drivioapp.model.ElectricCar
import nl.avans.drivioapp.model.User
import nl.avans.drivioapp.viewModel.AddElectricCarViewModel
import kotlin.random.Random


class AddElectricCarFragment : Fragment(R.layout.fragment_add_electric_car) {
    private val addElectricCarViewModel: AddElectricCarViewModel by viewModels();
    private var _binding: FragmentAddElectricCarBinding? = null;
    private val binding get() = _binding!!;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddElectricCarBinding.inflate(inflater, container, false);
        val view = binding.root;
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState);

        binding.postElectricCarBtn.setOnClickListener {

            val fastChargeSpeed = binding.etFastChargeSpeed.text.toString().toInt()
            val carRange = binding.etCarRange.text.toString().toInt()
            val chargeConnection = binding.etChargeConnection.text.toString()
            val buildYear = binding.etBuildYear.text.toString().toInt()
            val numberPlate = binding.etNumberPlate.text.toString()
            val chargeSpeed = binding.etChargeSpeed.text.toString().toInt()
            val carType = binding.etCarType.text.toString()
            val fuelType = binding.etFuelType.text.toString()
            val model = binding.etModel.text.toString()
            val whPerKm = binding.etWhPerKm.text.toString().toInt()
            val gearBox = binding.etGearBox.text.toString()
            val brand = binding.etBrand.text.toString()
            val randomLatitude = Random.nextDouble(52.2, 52.4)
            val randomLongitude = Random.nextDouble(4.7, 4.9)

            val electricCar = ElectricCar(
                null,
                fastChargeSpeed,
                carRange,
                chargeConnection,
                buildYear,
                numberPlate,
                chargeSpeed,
                carType,
                fuelType,
                model,
                whPerKm,
                gearBox,
                brand,
                User(23),
                randomLatitude,
                randomLongitude
            )
            addElectricCarViewModel.postElectricCar(electricCar);
        }
    }

    override fun onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }
}