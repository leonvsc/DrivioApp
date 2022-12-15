package nl.avans.drivioapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import nl.avans.drivioapp.adapter.AddCarAdapter
import nl.avans.drivioapp.databinding.FragmentAddCarBinding
import nl.avans.drivioapp.databinding.PostElectricCarBinding
import nl.avans.drivioapp.model.AddCar
import nl.avans.drivioapp.model.User
import nl.avans.drivioapp.viewModel.AddCarViewModel


class AddCarFragment : Fragment(R.layout.fragment_add_car) {
    private var _binding: FragmentAddCarBinding? = null;
    private var _bind: PostElectricCarBinding? = null;
    private val binding get() = _binding!!;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddCarBinding.inflate(inflater, container, false);
        _bind = PostElectricCarBinding.inflate(inflater,container, false)
        val view = binding.root;
        return view;
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState);

        val addCarViewModel: AddCarViewModel by viewModels();
        addCarViewModel.addCarResponse.observe(viewLifecycleOwner) {
            val obj = addCarViewModel.addCarResponse.value ?: listOf();
            val recyclerView = binding.recyclerView;
            recyclerView.adapter = AddCarAdapter(this, obj);
        }

//        binding.getCarsBtn.setOnClickListener {
//            addCarViewModel.getCars();
//        }


        val obj = addCarViewModel.addCarResponse.value ?: listOf();

        binding.postCarsBtn.setOnClickListener {
            val addCar = AddCar(
                obj[0].fastChargeSpeed,
                obj[0].carRange,
                obj[0].chargeConnection,
                obj[0].buildYear,
                obj[0].numberPlate,
                obj[0].chargeSpeed,
                obj[0].carType,
                obj[0].fuelType,
                obj[0].model,
                obj[0].whPerKm,
                obj[0].gearBox,
                obj[0].brand,
                User(23)
            )
            addCarViewModel.postElectricCars(addCar);
        }
    }

    override fun onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }
}