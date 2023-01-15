package nl.avans.drivioapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import nl.avans.drivioapp.R
import nl.avans.drivioapp.databinding.FragmentMyCarDetailsBinding
import nl.avans.drivioapp.viewModel.MyCarsViewModel

class MyCarDetailsFragment : Fragment(R.layout.fragment_my_car_details) {
    private var _binding: FragmentMyCarDetailsBinding? = null;
    private val binding get() = _binding!!;
    private val myCarsViewModel: MyCarsViewModel by viewModels()
    private var carId: Int? = null;


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyCarDetailsBinding.inflate(inflater, container, false);
        val view = binding.root;
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Assign the tv's and iv to a value
        val tvCarId: TextView = binding.tvCarId
        val tvBrand: TextView = binding.tvBrand
        val tvBuildYear: TextView = binding.tvBuildYear
        val tvModel: TextView = binding.tvModel
        val tvFuelType: TextView = binding.tvFuelType
        val ivUploadedImage: ImageView = binding.ivUploadedImage

        // Listen to the carId from the MyCarsFragment
        setFragmentResultListener("carId") { requestKey, bundle ->
            carId = bundle.getInt("carId")
            myCarsViewModel.getElectricCarById(carId!!)

            // Delete a car and give a response
            binding.btnDeleteCar.setOnClickListener {
                myCarsViewModel.deleteElectricCarWithResponse(carId!!)

                myCarsViewModel.deleteFuelCarResponse.observe(viewLifecycleOwner) {
                    val response = myCarsViewModel.deleteFuelCarResponse.value
//                  TODO: Make the button navigate to another page
                    if (response?.code() == 200) {
                        Toast.makeText(activity, "Deleted!!", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_myCarDetailsFragment_to_myCarsFragment)
                    } else {
                        Toast.makeText(activity, "Failed!!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        // Fill the tv's and iv with values from the DB
        myCarsViewModel.getElectricCarByIdResponse.observe(viewLifecycleOwner) {
            val myCar = myCarsViewModel.getElectricCarByIdResponse.value

            tvCarId.text = myCar?.body()?.carId.toString()
            tvBrand.text = myCar?.body()?.brand.toString()
            tvBuildYear.text = myCar?.body()?.buildYear.toString()
            tvModel.text = myCar?.body()?.model.toString()
            tvFuelType.text = myCar?.body()?.fuelType.toString()
            val url = "https://images-drivio-app.s3.eu-west-1.amazonaws.com/${myCar?.body()?.imageUrl}"
            Picasso.get().load(url).into(ivUploadedImage)
        }
        // Set the carId as a result to use it in the update fragment
        binding.btnUpdateCar.setOnClickListener{
            setFragmentResult(
                "carDetailsId",
                bundleOf("carDetailsId" to carId)
            )
            findNavController().navigate(R.id.action_myCarDetailsFragment_to_updateCarFragment)
        }

        // Navigate to the calculate tco tab
        binding.btnCalculateTco.setOnClickListener{
            findNavController().navigate(R.id.action_myCarDetailsFragment_to_calculateTCOFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}