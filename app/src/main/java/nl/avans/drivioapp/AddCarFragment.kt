package nl.avans.drivioapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import nl.avans.drivioapp.adapter.AddCarAdapter
import nl.avans.drivioapp.databinding.FragmentDiscoverBinding
import nl.avans.drivioapp.viewModel.AddCarViewModel


class AddCarFragment : Fragment(R.layout.fragment_add_car) {
    private var _binding: FragmentDiscoverBinding? = null;
    private val binding get() = _binding!!;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDiscoverBinding.inflate(inflater, container, false);
        val view = binding.root;
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val addCarViewModel: AddCarViewModel by viewModels()
        addCarViewModel.addCarResponse.observe(viewLifecycleOwner) {
            val obj = addCarViewModel.addCarResponse.value ?: listOf()
            val recyclerView = binding.recyclerView
            recyclerView.adapter = AddCarAdapter(this, obj)
        }

        binding.getBtn.setOnClickListener {
            addCarViewModel.getCars()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}