package nl.avans.drivioapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import nl.avans.drivioapp.databinding.FragmentAddElectricCarBinding
import nl.avans.drivioapp.databinding.FragmentMyCarDetailsBinding
import nl.avans.drivioapp.databinding.PostElectricCarBinding
import nl.avans.drivioapp.model.ElectricCar
import nl.avans.drivioapp.model.UserElectricCar
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
            carId = bundle.getInt("carDetailsId")
            myCarsViewModel.getElectricCarById(carId!!)
        }

        myCarsViewModel.getElectricCarByIdResponse.observe(viewLifecycleOwner) {
            val myCar = myCarsViewModel.getElectricCarByIdResponse.value
            binding.etFastChargeSpeed.setText(myCar?.body()?.fastChargeSpeed.toString().toInt())
            binding.etCarRange.setText(myCar?.body()?.carRange.toString().toInt())
            binding.etChargeConnection.setText(myCar?.body()?.chargeConnection.toString())
            binding.etBuildYear.setText(myCar?.body()?.buildYear.toString().toInt())
            binding.etNumberPlate.setText(myCar?.body()?.numberPlate.toString())
            binding.etChargeSpeed.setText(myCar?.body()?.chargeSpeed.toString().toInt())
            binding.etCarType.setText(myCar?.body()?.carType.toString())
            binding.etFuelType.setText(myCar?.body()?.fuelType.toString())
            binding.etModel.setText(myCar?.body()?.model.toString())
            binding.etWhPerKm.setText(myCar?.body()?.whPerKm.toString().toInt())
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
                UserElectricCar(23)
            )
            addElectricCarViewModel.postElectricCar(electricCar);
        }

    }

        private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.flFragment, fragment)
        fragmentTransaction?.commit()
    }
}


