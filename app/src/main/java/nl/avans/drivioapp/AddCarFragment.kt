package nl.avans.drivioapp

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import nl.avans.drivioapp.databinding.FragmentAddCarBinding
import nl.avans.drivioapp.databinding.PostElectricCarBinding
import nl.avans.drivioapp.model.AddCar
import nl.avans.drivioapp.model.User
import nl.avans.drivioapp.viewModel.AddCarViewModel


class AddCarFragment : Fragment(R.layout.fragment_add_car) {
    private val addCarViewModel: AddCarViewModel by viewModels();
    private var _binding: FragmentAddCarBinding? = null;
    private var _bind: PostElectricCarBinding? = null;
    private val binding get() = _binding!!;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddCarBinding.inflate(inflater, container, false);
        _bind = PostElectricCarBinding.inflate(inflater, container, false)
        val view = binding.root;
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState);
        val testing: String = "Test"
        binding.etCarRange.setText(testing);


//        val myTextBox = findViewById(R.id.editBox) as EditText
//        myTextBox.setText("My Product Description")


//        binding.getCarsBtn.setOnClickListener {
//            addCarViewModel.getCars();
//        }

//        val etCarType = _bind?.etCarType?.text

        binding.postCarsBtn.setOnClickListener {

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

            val addCar = AddCar(
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
                User(23)
            )
            addCarViewModel.postElectricCars(addCar);
        }
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView();
//        _binding = null;
//    }
    }
}