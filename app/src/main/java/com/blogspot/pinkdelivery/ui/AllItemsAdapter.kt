package com.blogspot.pinkdelivery.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.pinkdelivery.R
import com.blogspot.pinkdelivery.databinding.LayoutPinkItemBinding
import com.blogspot.pinkdelivery.models.PinkItems

class AllItemsAdapter(private val viewModel: AllViewModel):
    ListAdapter<PinkItems, AllItemsAdapter.ViewHolder>(DiffCallback){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutPinkItemBinding.inflate(LayoutInflater.from(parent.context)),viewModel)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }
    class ViewHolder(private val binding: LayoutPinkItemBinding,private val viewModel: AllViewModel):
        RecyclerView.ViewHolder(binding.root){
        private var itemCount = 0
        private var bookmark = false
        fun bind(item: PinkItems){
            binding.model = item
            binding.itemAdd.setOnClickListener {
                itemCount += 1
                binding.itemCount.text = itemCount.toString()
            }
            binding.itemSub.setOnClickListener {
                if (itemCount>0){
                    itemCount -= 1
                    binding.itemCount.text = itemCount.toString()
                }
            }
            binding.itemBookmark.setOnClickListener {
                bookmark = if(!bookmark) {
                    binding.itemBookmark.setImageResource(R.drawable.ic_baseline_favorite_24)
                    true
                } else {
                    binding.itemBookmark.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                    false
                }
            }

            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<PinkItems>() {
        override fun areItemsTheSame(oldItem: PinkItems, newItem: PinkItems): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: PinkItems, newItem: PinkItems): Boolean {
            return oldItem.id == newItem.id
        }
    }

}