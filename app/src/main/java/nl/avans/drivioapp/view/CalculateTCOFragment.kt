package nl.avans.drivioapp.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import nl.avans.drivioapp.R
import nl.avans.drivioapp.databinding.FragmentCalculateTcoBinding


class CalculateTCOFragment : Fragment(R.layout.fragment_calculate_tco) {
    private var _binding: FragmentCalculateTcoBinding? = null
    private val binding get() = _binding!!
    private var TCO: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalculateTcoBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvTCO: TextView = binding.tvTCO
        val tvUsageCosts: TextView = binding.tvUsageCosts

        // Button to calculate the TCO when values are filled in
        binding.btnCalculateTco.setOnClickListener {
            val newPrice = binding.etNewPrice.text.toString().toInt()
            val additionalCosts = binding.etAdditionalCosts.text.toString().toInt()
            val kilometersPerYear = binding.etKilometersPerYear.text.toString().toInt()
            println("Newprice = $newPrice")
            println("Additional costs = $additionalCosts")
            TCO = (newPrice + additionalCosts) / kilometersPerYear + newPrice
            tvTCO.text = TCO.toString()
        }

        // Button to calculate the usage costs when values are filled in
        binding.btnCalculateUsageCosts.setOnClickListener {
            val yearsOfUse = binding.etYearsOfUse.text.toString().toInt()
            val fuelType = binding.etFuelType.text.toString()
            if (fuelType == "Benzine") {
                val usageCosts = TCO?.div(yearsOfUse)?.div(2)
                tvUsageCosts.text = usageCosts.toString()
            } else if (fuelType == "Electrisch") {
                val usageCosts = TCO?.div(yearsOfUse)?.div(4)
                tvUsageCosts.text = usageCosts.toString()
            } else if (fuelType == "Waterstof") {
                val usageCosts = TCO?.div(yearsOfUse)?.div(6)
                tvUsageCosts.text = usageCosts.toString()
            }
        }
    }
}