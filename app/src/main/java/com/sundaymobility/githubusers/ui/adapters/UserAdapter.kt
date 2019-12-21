package com.sundaymobility.githubusers.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sundaymobility.githubusers.R
import com.sundaymobility.githubusers.databinding.UserListItemBinding
import com.sundaymobility.githubusers.ui.view_data_model.UserListItemViewData

class UserAdapter : ListAdapter<UserListItemViewData, UserAdapter.RecyclerViewHolder>(object :
    DiffUtil.ItemCallback<UserListItemViewData>() {

    override fun areItemsTheSame(
        oldItem: UserListItemViewData,
        newItem: UserListItemViewData
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: UserListItemViewData,
        newItem: UserListItemViewData
    ): Boolean {
        return oldItem == newItem
    }
}) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {



        val binding:UserListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.user_list_item,
            parent,
            false
        )

        return RecyclerViewHolder(binding)

    }


    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {

        holder.bind(getItem(position))


    }


    inner class RecyclerViewHolder(private val binding: UserListItemBinding) :
        RecyclerView.ViewHolder(binding.getRoot()) {


        fun bind(dataModel: UserListItemViewData) {
            binding.model = dataModel
            binding.executePendingBindings()
        }
    }


}