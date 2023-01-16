package nl.avans.drivioapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import nl.avans.drivioapp.R
import nl.avans.drivioapp.model.User
import nl.avans.drivioapp.view.UserListFragmentDirections

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    private var userList = emptyList<User>()

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_row, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.findViewById<TextView>(R.id.txt_id).text = currentItem.id.toString()
        holder.itemView.findViewById<TextView>(R.id.txt_email).text = currentItem.email
        holder.itemView.findViewById<TextView>(R.id.txt_password).text = currentItem.password

        holder.itemView.findViewById<View>(R.id.user_row_layout).setOnClickListener {
            val action =
                UserListFragmentDirections.actionUserListFragmentToUpdateUFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int = userList.size

    fun setData(user: List<User>) {
        this.userList = user
        notifyDataSetChanged()
    }
}
