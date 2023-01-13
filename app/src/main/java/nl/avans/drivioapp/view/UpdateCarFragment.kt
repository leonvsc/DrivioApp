package nl.avans.drivioapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import nl.avans.drivioapp.R
import nl.avans.drivioapp.databinding.FragmentAddElectricCarBinding
import nl.avans.drivioapp.model.ElectricCar
import nl.avans.drivioapp.model.User1
import nl.avans.drivioapp.viewModel.AddElectricCarViewModel
import nl.avans.drivioapp.viewModel.MyCarsViewModel

class UpdateCarFragment : Fragment(R.layout.fragment_add_electric_car) {
    private val addElectricCarViewModel: AddElectricCarViewModel by viewModels();
    private var _binding: FragmentAddElectricCarBinding? = null;
    private val binding get() = _binding!!;
    private val myCarsViewModel: MyCarsViewModel by viewModels()
    private var carId: Int? = null;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddElectricCarBinding.inflate(inflater, container, false);
        val view = binding.root;
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener("carDetailsId") { requestKey, bundle ->
            val carId = bundle.getInt("carDetailsId")
            myCarsViewModel.getElectricCarById(carId)
        }

        myCarsViewModel.getElectricCarByIdResponse.observe(viewLifecycleOwner) {
            val myCar = myCarsViewModel.getElectricCarByIdResponse.value
            binding.etFastChargeSpeed.setText(myCar?.body()?.fastChargeSpeed.toString())
            binding.etCarRange.setText(myCar?.body()?.carRange.toString())
            binding.etChargeConnection.setText(myCar?.body()?.chargeConnection.toString())
            binding.etBuildYear.setText(myCar?.body()?.buildYear.toString())
            binding.etNumberPlate.setText(myCar?.body()?.numberPlate.toString())
            binding.etChargeSpeed.setText(myCar?.body()?.chargeSpeed.toString())
            binding.etCarType.setText(myCar?.body()?.carType.toString())
            binding.etFuelType.setText(myCar?.body()?.fuelType.toString())
            binding.etModel.setText(myCar?.body()?.model.toString())
            binding.etWhPerKm.setText(myCar?.body()?.whPerKm.toString())
            binding.etGearBox.setText(myCar?.body()?.gearBox.toString())
            binding.etBrand.setText(myCar?.body()?.brand.toString())
        }

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

            val electricCar = ElectricCar(
                carId,
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
                User1(23)
            )
            addElectricCarViewModel.putElectricCarWithResponse(electricCar)

            addElectricCarViewModel.putElectricCarWithResponse.observe(viewLifecycleOwner) {
                val response = addElectricCarViewModel.putElectricCarWithResponse.value
//              TODO: Make switch to other fragment after put
                if (response?.code() == 200) {
                    Toast.makeText(activity, "Success!!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(activity, "Failed!!", Toast.LENGTH_SHORT).show()
                }
        }

    }
    }
}


