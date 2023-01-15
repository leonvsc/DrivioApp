package nl.avans.drivioapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import nl.avans.drivioapp.R
import nl.avans.drivioapp.adapter.MyCarsAdapter
import nl.avans.drivioapp.databinding.FragmentMyCarsBinding
import nl.avans.drivioapp.model.ElectricCar
import nl.avans.drivioapp.viewModel.MyCarsViewModel

class MyCarsFragment : Fragment(R.layout.fragment_my_cars),
    MyCarsAdapter.OnItemClickListener {
    private var _binding: FragmentMyCarsBinding? = null;
    private val binding get() = _binding!!;
    private val myCarsViewModel: MyCarsViewModel by viewModels();
    private lateinit var myCar: List<ElectricCar>;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyCarsBinding.inflate(inflater, container, false);
        val view = binding.root;
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myCarsViewModel.electricCarResponse.observe(viewLifecycleOwner) {
            myCar = myCarsViewModel.electricCarResponse.value ?: listOf()
            val recyclerView = binding.recyclerView
            recyclerView.adapter = MyCarsAdapter(myCar, this)
        }

        val swipeRefreshLayout = binding.swipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            myCarsViewModel.getElectricCars()
        }

        binding.btnAddCar.setOnClickListener{
            findNavController().navigate(R.id.action_myCarsFragment_to_addElectricCarFragment)
        }
    }

    override fun onItemClick(position: Int) {
        myCarsViewModel.electricCarResponse.observe(viewLifecycleOwner) {
            setFragmentResult(
                "carId",
                bundleOf("carId" to myCar[position].carId)
            )
        }
        findNavController().navigate(R.id.action_myCarsFragment_to_myCarDetailsFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}