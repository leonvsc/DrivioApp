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


class AddCarFragment : Fragment(R.layout.post_electric_car) {
//    private var _binding: FragmentAddCarBinding? = null;
//    private var _bind: PostElectricCarBinding? = null;
//    private val binding get() = _binding!!;
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentAddCarBinding.inflate(inflater, container, false);
//        _bind = PostElectricCarBinding.inflate(inflater, container, false)
//        val view = binding.root;
//        return view;
//    }

//    fun loadAffirmations(): List<Affirmation> {
//        return listOf<Affirmation>(
//            Affirmation(R.string.affirmation1),
//            Affirmation(R.string.affirmation2),
//            Affirmation(R.string.affirmation3),
//            Affirmation(R.string.affirmation4),
//            Affirmation(R.string.affirmation5),
//            Affirmation(R.string.affirmation6),
//            Affirmation(R.string.affirmation7),
//            Affirmation(R.string.affirmation8),
//            Affirmation(R.string.affirmation9),
//            Affirmation(R.string.affirmation10)
//    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState);
//
//        val addCarViewModel: AddCarViewModel by viewModels();
//        addCarViewModel.addCarResponse.observe(viewLifecycleOwner) {
//
//            val recyclerView = binding.recyclerView;
//            val obj = AddCarAdapter(this,);
//            recyclerView.adapter = AddCarAdapter(this);
//        }
//
////        binding.getCarsBtn.setOnClickListener {
////            addCarViewModel.getCars();
////        }
//
////        val etCarType = _bind?.etCarType?.text
//
//        binding.postCarsBtn.setOnClickListener {
//            val addCar = AddCar(
//                2,
//                2,
//                "Thing",
//                20044,
//                "hello",
//                5,
//                "Car",
//                "Electric",
//                "Car",
//                10,
//                "Manuel",
//                "Opel",
//                User(23)
//            )
//            addCarViewModel.postElectricCars(addCar);
//        }
////    }
////
////    override fun onDestroyView() {
////        super.onDestroyView();
////        _binding = null;
////    }
//    }
}