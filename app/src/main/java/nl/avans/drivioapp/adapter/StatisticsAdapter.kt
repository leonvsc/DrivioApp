package nl.avans.drivioapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nl.avans.drivioapp.databinding.ListStatisticsBinding
import nl.avans.drivioapp.model.Statistic

class StatisticsAdapter(
    private val dataset: List<Statistic>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<StatisticsAdapter.StatisticsViewHolder>() {

    inner class StatisticsViewHolder(binding: ListStatisticsBinding) :
        RecyclerView.ViewHolder(binding.root), OnClickListener {
        val tvId: TextView = binding.tvId
        val tvName: TextView = binding.tvName
        val tvValue: TextView = binding.tvValue

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatisticsViewHolder {
        val binding = ListStatisticsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return StatisticsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StatisticsViewHolder, position: Int) {
        val item = dataset[position]
        holder.tvId.text = item.statisticId.toString()
        holder.tvName.text = item.name
        holder.tvValue.text = item.value
    }

    override fun getItemCount() = dataset.size

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}