package nl.avans.drivioapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nl.avans.drivioapp.DiscoverFragment
import nl.avans.drivioapp.R
import nl.avans.drivioapp.model.Advertisement

class AdvertisementAdapter(private val context: DiscoverFragment, private val dataset: List<Advertisement>) : RecyclerView.Adapter<AdvertisementAdapter.AdvertisementViewHolder>() {

    class AdvertisementViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvDescription: TextView = view.findViewById(R.id.tvDescription)
        val tvPrice: TextView = view.findViewById(R.id.tvPrice)
        val tvStartDate: TextView = view.findViewById(R.id.tvStartDate)
        val tvEndDate: TextView = view.findViewById(R.id.tvEndDate)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdvertisementViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_advertisement, parent, false)

        return AdvertisementViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: AdvertisementViewHolder, position: Int) {
        val item = dataset[position]
        holder.tvTitle.text = item.title
        holder.tvDescription.text = item.description
        holder.tvPrice.text = item.price.toString()
        holder.tvStartDate.text = item.startDate
        holder.tvEndDate.text = item.endDate

    }

    override fun getItemCount() = dataset.size

}