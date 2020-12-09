package com.baseproject.ui.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.baseproject.domain.model.dto.User
import com.baseproject.databinding.ViewHolderUserBinding as Binding

class UserAdapter(
    private val openDetails: (itemPosition: Int) -> Unit
) : ListAdapter<User, UserAdapter.ItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemPersonBinding = Binding.inflate(layoutInflater, parent, false)
        return ItemViewHolder(itemPersonBinding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    inner class ItemViewHolder(private val binding: Binding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: User, position: Int) = with(itemView) {
            binding.tvFirstName.text = item.firstName
            binding.tvLastName.text = item.lastName
            setOnClickListener { openDetails(position) }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean = oldItem == newItem
    }
}