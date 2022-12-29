package nl.avans.drivioapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nl.avans.drivioapp.MyCarsFragment
import nl.avans.drivioapp.R
import nl.avans.drivioapp.model.ElectricCar


class MyCarsAdapter(private val context: MyCarsFragment, private val dataset: List<ElectricCar>) : RecyclerView.Adapter<MyCarsAdapter.MyCarViewHolder>() {

    class MyCarViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val tvBrand: TextView = view.findViewById(R.id.tvBrand)
        val tvModel: TextView = view.findViewById(R.id.tvModel)
        val tvFuelType: TextView = view.findViewById(R.id.tvFuelType)
        val tvBuildYear: TextView = view.findViewById(R.id.tvBuildYear)
        val tvNumberPlate: TextView = view.findViewById(R.id.tvNumberPlate)
        val tvCarType: TextView = view.findViewById(R.id.tvNumberPlate)
        val tvGearbox: TextView = view.findViewById(R.id.tvGearbox)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCarViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_electric_car, parent, false)

        return MyCarViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: MyCarViewHolder, position: Int) {
        val item = dataset[position]
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
