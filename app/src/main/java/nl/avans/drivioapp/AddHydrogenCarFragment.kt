package nl.avans.drivioapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import nl.avans.drivioapp.databinding.FragmentAddHydrogenCarBinding
import nl.avans.drivioapp.model.HydrogenCar
import nl.avans.drivioapp.model.User
import nl.avans.drivioapp.viewModel.AddHydrogenCarViewModel

class AddHydrogenCarFragment : Fragment(R.layout.fragment_add_hydrogen_car) {
    private val addHydrogenCarViewModel: AddHydrogenCarViewModel by viewModels();
    private var _binding: FragmentAddHydrogenCarBinding? = null;
    private val binding get() = _binding!!;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddHydrogenCarBinding.inflate(inflater, container, false);
        val view = binding.root;
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState);

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
                User(23)
            )
            addHydrogenCarViewModel.postHydrogenCar(hydrogenCar);
        }
    }
    override fun onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }
}