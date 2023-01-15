package nl.avans.drivioapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nl.avans.drivioapp.databinding.ListReservationBinding
import nl.avans.drivioapp.model.Reservation

class ReservationAdapter(
    private val dataset: List<Reservation>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<ReservationAdapter.ReservationViewHolder>() {

    inner class ReservationViewHolder(binding: ListReservationBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        val tvTitle: TextView = binding.tvTitle
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

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReservationAdapter.ReservationViewHolder {
        val binding =
            ListReservationBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ReservationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReservationAdapter.ReservationViewHolder, position: Int) {
        val item = dataset[position]
        holder.tvTitle.text = item.advertisement.title
        holder.tvStartDate.text = item.startDate
        holder.tvEndDate.text = item.endDate

    }

    override fun getItemCount() = dataset.size

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}