package nl.avans.drivioapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import nl.avans.drivioapp.databinding.ListAdvertisementBinding
import nl.avans.drivioapp.model.Advertisement

class AdvertisementAdapter(
//    private val context: DiscoverFragment,
    private val dataset: List<Advertisement>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<AdvertisementAdapter.AdvertisementViewHolder>() {

    inner class AdvertisementViewHolder(binding: ListAdvertisementBinding) :
        RecyclerView.ViewHolder(binding.root), OnClickListener {
        val tvTitle: TextView = binding.tvTitle
        val tvDescription: TextView = binding.tvDescription
        val tvPrice: TextView = binding.tvPrice
        val tvStartDate: TextView = binding.tvStartDate
        val tvEndDate: TextView = binding.tvEndDate
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdvertisementViewHolder {
        val binding =
            ListAdvertisementBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return AdvertisementViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdvertisementViewHolder, position: Int) {
        val item = dataset[position]
        holder.tvTitle.text = item.title
        holder.tvDescription.text = item.description
        holder.tvPrice.text = item.price.toString()
        holder.tvStartDate.text = item.startDate
        holder.tvEndDate.text = item.endDate
        val url = "https://images-drivio-app.s3.eu-west-1.amazonaws.com/${item.electricCar?.imageUrl}"
        Picasso.get().load(url).into(holder.ivUploadedImage)
    }

    override fun getItemCount() = dataset.size

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}