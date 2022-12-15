package nl.avans.drivioapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nl.avans.drivioapp.AddCarFragment
import nl.avans.drivioapp.R
import nl.avans.drivioapp.databinding.FragmentAddCarBinding
import nl.avans.drivioapp.databinding.ListElectricCarBinding
import nl.avans.drivioapp.databinding.PostElectricCarBinding
import nl.avans.drivioapp.model.AddCar

class AddCarAdapter(private val context: AddCarFragment, private val dataset: List<AddCar>) : RecyclerView.Adapter<AddCarAdapter.AddCarViewHolder>() {

//    class AddCarViewHolder(val binding: ListElectricCarBinding) : RecyclerView.ViewHolder(binding.root) {
//        val tvBrand: TextView = binding.tvBrand
//        val tvModel: TextView = binding.tvModel
//        val tvFuelType: TextView = binding.tvFuelType
//        val tvBuildYear: TextView = binding.tvBuildYear
//        val tvNumberPlate: TextView = binding.tvNumberPlate
//        val tvCarType: TextView = binding.tvCarType
//        val tvGearbox: TextView = binding.tvGearbox
//
//    }

    class AddCarViewHolder(val binding: PostElectricCarBinding) : RecyclerView.ViewHolder(binding.root) {
        val etFastChargeSpeed: EditText = binding.etFastChargeSpeed
        val etCarRange: EditText = binding.etCarRange
        val etChargeConnection: EditText = binding.etChargeConnection
        val etBuildYear: EditText = binding.etBuildYear
        val etNumberPlate: EditText = binding.etNumberPlate
        val etChargeSpeed: EditText = binding.etChargeSpeed
        val etCarType: EditText = binding.etCarType
        val etFuelType: EditText = binding.etFuelType
        val etModel: EditText = binding.etModel
        val etWhPerKm: EditText = binding.etWhPerKm
        val etGearBox: EditText = binding.etGearBox
        val etBrand: EditText = binding.etBrand

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddCarViewHolder {
        val binding = PostElectricCarBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return AddCarViewHolder(binding)
    }

//    override fun onBindViewHolder(holder: AddCarViewHolder, position: Int) {
//        val item = dataset[position]
//        holder.tvBrand.text = item.brand
//        holder.tvModel.text = item.model
//        holder.tvFuelType.text = item.fuelType
//        holder.tvBuildYear.text = item.buildYear.toString()
//        holder.tvNumberPlate.text = item.numberPlate
//        holder.tvCarType.text = item.carType
//        holder.tvGearbox.text = item.gearBox
//    }

    override fun onBindViewHolder(holder: AddCarViewHolder, position: Int) {
        holder.etFastChargeSpeed.text
        holder.etCarRange.text
        holder.etBuildYear.text
        holder.etNumberPlate.text
        holder.etChargeConnection.text
        holder.etChargeSpeed.text
        holder.etCarType.text
        holder.etFuelType.text
        holder.etModel.text
        holder.etWhPerKm.text
        holder.etGearBox.text
        holder.etBrand.text

    }

    override fun getItemCount() = dataset.size

}
