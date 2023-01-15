package nl.avans.drivioapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import nl.avans.drivioapp.view.MyCarsFragment
import nl.avans.drivioapp.databinding.ListElectricCarBinding
import nl.avans.drivioapp.model.ElectricCar


class MyCarsAdapter(
//    private val context: MyCarsFragment,
    private val dataset: List<ElectricCar>,
    private val listener: OnItemClickListener
    ) : RecyclerView.Adapter<MyCarsAdapter.MyCarViewHolder>() {

    inner class MyCarViewHolder(private val binding: ListElectricCarBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        val tvBrand: TextView = binding.tvBrand
        val tvModel: TextView = binding.tvModel
        val tvFuelType: TextView = binding.tvFuelType
        val tvBuildYear: TextView = binding.tvBuildYear
        val tvNumberPlate: TextView = binding.tvNumberPlate
        val tvCarType: TextView = binding.tvNumberPlate
        val tvGearbox: TextView = binding.tvGearbox
        val ivUploadedImage: ImageView = binding.ivUploadedImage

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCarViewHolder {
        val binding = ListElectricCarBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyCarViewHolder(binding)
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
        val url = "https://images-drivio-app.s3.eu-west-1.amazonaws.com/${item.imageUrl}"
        Picasso.get().load(url).into(holder.ivUploadedImage)
    }

    override fun getItemCount() = dataset.size

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}
