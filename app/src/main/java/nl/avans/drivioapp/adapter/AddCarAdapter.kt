package nl.avans.drivioapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nl.avans.drivioapp.AddCarFragment
import nl.avans.drivioapp.R
import nl.avans.drivioapp.databinding.FragmentAddCarBinding
import nl.avans.drivioapp.databinding.ListElectricCarBinding
import nl.avans.drivioapp.model.AddCar

class AddCarAdapter(private val context: AddCarFragment, private val dataset: List<AddCar>) : RecyclerView.Adapter<AddCarAdapter.AddCarViewHolder>() {

    class AddCarViewHolder(val binding: ListElectricCarBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvCarId: TextView = binding.tvCarId
        val tvBrand: TextView = binding.tvBrand
        val tvModel: TextView = binding.tvModel
        val tvFuelType: TextView = binding.tvFuelType
        val tvBuildYear: TextView = binding.tvBuildYear
        val tvNumberPlate: TextView = binding.tvNumberPlate
        val tvCarType: TextView = binding.tvCarType
        val tvGearbox: TextView = binding.tvGearbox

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddCarViewHolder {
        val binding = ListElectricCarBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return AddCarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddCarViewHolder, position: Int) {
        val item = dataset[position]
        holder.tvCarId.text = item.carId.toString()
        holder.tvBrand.text = item.brand
        holder.tvModel.text = item.model
        holder.tvFuelType.text = item.fuelType
        holder.tvBuildYear.text = item.buildYear.toString()
        holder.tvNumberPlate.text = item.numberPlate
        holder.tvCarType.text = item.carType
        holder.tvGearbox.text = item.gearBox
    }

    override fun getItemCount() = dataset.size

}
