package com.francisco.catjokes.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.francisco.catjokes.R
import com.francisco.catjokes.databinding.CatItemBinding
import com.francisco.catjokes.list.domain.CatJoke

class CatsAdapter : ListAdapter<CatJoke, CatsAdapter.CatViewHolder>(CatDiffCallback) {

    class CatViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = CatItemBinding.bind(view)

        fun bind(catJoke: CatJoke) {
            binding.catText.text = catJoke.joke
            Glide
                .with(itemView.context)
                .load(catJoke.catImageUrl)
                .fitCenter()
                .into(binding.catImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cat_item, parent, false)
        return CatViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}


object CatDiffCallback : DiffUtil.ItemCallback<CatJoke>() {
    override fun areItemsTheSame(oldItem: CatJoke, newItem: CatJoke): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CatJoke, newItem: CatJoke): Boolean {
        return oldItem.id == newItem.id
    }
}