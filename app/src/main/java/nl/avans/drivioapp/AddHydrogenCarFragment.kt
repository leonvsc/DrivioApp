package nl.avans.drivioapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import nl.avans.drivioapp.databinding.FragmentAddElectricCarBinding
import nl.avans.drivioapp.databinding.FragmentAddHydrogenCarBinding
import nl.avans.drivioapp.databinding.PostElectricCarBinding
import nl.avans.drivioapp.model.ElectricCar
import nl.avans.drivioapp.model.HydrogenCar
import nl.avans.drivioapp.model.UserElectricCar
import nl.avans.drivioapp.model.UserHydrogenCar
import nl.avans.drivioapp.viewModel.AddElectricCarViewModel
import nl.avans.drivioapp.viewModel.AddHydrogenCarViewModel

class AddHydrogenCarFragment : Fragment(R.layout.fragment_add_hydrogen_car) {
    private val addHydrogenCarViewModel: AddHydrogenCarViewModel by viewModels();
    private var _binding: FragmentAddHydrogenCarBinding? = null;
    private var _bind: FragmentAddHydrogenCarBinding? = null;
    private val binding get() = _binding!!;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddHydrogenCarBinding.inflate(inflater, container, false);
        _bind = FragmentAddHydrogenCarBinding.inflate(inflater, container, false)
        val view = binding.root;
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState);
//        val testing: String = "Test"
//        binding.etCarRange.setText(testing);


//        val myTextBox = findViewById(R.id.editBox) as EditText
//        myTextBox.setText("My Product Description")


//        binding.getCarsBtn.setOnClickListener {
//            addCarViewModel.getCars();
//        }

//        val etCarType = _bind?.etCarType?.text

        binding.postHydrogenCarBtn.setOnClickListener {

            val kgPer100Km = binding.etKgPer100Km.text.toString().toInt()
            val carRange = binding.etCarRange.text.toString().toInt()
            val buildYear = binding.etBuildYear.text.toString().toInt()
            val numberPlate = binding.etNumberPlate.text.toString()
            val tankSpeed = binding.etTankSpeed.text.toString().toInt()
            val carType = binding.etCarType.text.toString()
            val fuelType = binding.etFuelType.text.toString()
            val model = binding.etModel.text.toString()
            val tankSize = binding.etTankSize.text.toString().toInt()
            val gearBox = binding.etGearBox.text.toString()
            val brand = binding.etBrand.text.toString()

            val hydrogenCar = HydrogenCar(
                carRange,
                tankSpeed,
                buildYear,
                kgPer100Km,
                numberPlate,
                tankSize,
                carType,
                fuelType,
                model,
                gearBox,
                brand,
                UserHydrogenCar(23)
            )
            addHydrogenCarViewModel.postHydrogenCar(hydrogenCar);

//            addHydrogenCarViewModel.postHydrogenCar.observe() {
//                val response = advertisementViewModel.postAdvertisementResponse.value
//
//                if (response?.code() == 200) {
//                    Toast.makeText(activity, "Success!!", Toast.LENGTH_SHORT).show()
//                } else {
//                    Toast.makeText(activity, "Failed!!", Toast.LENGTH_SHORT).show()
//                }
//
//            }
        }
    }
}