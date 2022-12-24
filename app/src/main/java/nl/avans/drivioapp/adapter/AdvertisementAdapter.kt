package nl.avans.drivioapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nl.avans.drivioapp.DiscoverFragment
import nl.avans.drivioapp.databinding.ListAdvertisementBinding
import nl.avans.drivioapp.model.Advertisement
import java.text.SimpleDateFormat
import java.util.*

class AdvertisementAdapter(
    private val context: DiscoverFragment,
    private val dataset: List<Advertisement>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<AdvertisementAdapter.AdvertisementViewHolder>() {

    inner class AdvertisementViewHolder(private val binding: ListAdvertisementBinding) :
        RecyclerView.ViewHolder(binding.root), OnClickListener {
        val tvTitle: TextView = binding.tvTitle
        val tvDescription: TextView = binding.tvDescription
        val tvPrice: TextView = binding.tvPrice
        val tvStartDate: TextView = binding.tvStartDate
        val tvEndDate: TextView = binding.tvEndDate

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
        val sdf = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
        val item = dataset[position]
        holder.tvTitle.text = item.title
        holder.tvDescription.text = item.description
        holder.tvPrice.text = item.price.toString()
        holder.tvStartDate.text = item.startDate
        holder.tvEndDate.text = item.endDate

    }

    override fun getItemCount() = dataset.size

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}