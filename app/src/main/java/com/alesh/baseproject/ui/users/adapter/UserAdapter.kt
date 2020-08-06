package com.alesh.baseproject.ui.users.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alesh.baseproject.R
import com.alesh.domain.model.dto.User

class UserAdapter(
    private val openDetails: (itemPosition: Int) -> Unit
) : ListAdapter<User, UserAdapter.ItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_holder_shipment, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    inner class ItemViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        private val tvFirstName = item.findViewById(R.id.tvFirstName) as TextView
        private val tvLastName = item.findViewById(R.id.tvLastName) as TextView

        fun bind(item: User, position: Int) = with(itemView) {

            tvFirstName.text = item.firstName
            tvLastName.text = item.lastName

            setOnClickListener { openDetails(position) }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem == newItem
    }
}

