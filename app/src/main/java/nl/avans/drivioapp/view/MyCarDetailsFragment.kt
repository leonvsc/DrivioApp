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
    private var TCO: Int? = null

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

        val tvCarId: TextView = binding.tvCarId
        val tvBrand: TextView = binding.tvBrand
        val tvBuildYear: TextView = binding.tvBuildYear
        val tvModel: TextView = binding.tvModel
        val tvFuelType: TextView = binding.tvFuelType
        val ivUploadedImage: ImageView = binding.ivUploadedImage
        val tvTCO: TextView = binding.tvTCO
        val tvUsageCosts: TextView = binding.tvUsageCosts




        binding.btnCalculateTco.setOnClickListener {
            val newPrice = binding.etNewPrice.text.toString().toInt()
            val additionalCosts = binding.etAdditionalCosts.text.toString().toInt()
            println("Newprice = $newPrice")
            println("Additional costs = $additionalCosts")
            TCO = newPrice + additionalCosts
            tvTCO.text = TCO.toString()
        }

        binding.btnCalculateUsageCosts.setOnClickListener {
            val yearsOfUse = binding.etYearsOfUse.text.toString().toInt()
            println("Years of use $yearsOfUse")
            val usageCosts = TCO?.div(yearsOfUse)
            tvUsageCosts.text = usageCosts.toString()
        }

        setFragmentResultListener("carId") { requestKey, bundle ->
            carId = bundle.getInt("carId")
            myCarsViewModel.getElectricCarById(carId!!)

            binding.btnDeleteCar.setOnClickListener {
                myCarsViewModel.deleteElectricCarWithResponse(carId!!)

                myCarsViewModel.deleteFuelCarResponse.observe(viewLifecycleOwner) {
                    val response = myCarsViewModel.deleteFuelCarResponse.value
//                  TODO: Make the button navigate to another page
                    if (response?.code() == 200) {
                        Toast.makeText(activity, "Deleted!!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(activity, "Failed!!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

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
        binding.btnUpdateCar.setOnClickListener{
            setFragmentResult(
                "carDetailsId",
                bundleOf("carDetailsId" to carId)
            )
            findNavController().navigate(R.id.action_myCarDetailsFragment_to_updateCarFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}