package com.francisco.catjokes.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.francisco.catjokes.R
import com.francisco.catjokes.databinding.CatItemBinding
import com.francisco.catjokes.list.domain.CatJoke

class CatsAdapter :
    ListAdapter<CatJoke, CatsAdapter.CatViewHolder>(CatDiffCallback) {

    class CatViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = CatItemBinding.bind(view)
        private var currentCatJoke: CatJoke? = null

        init {
            view.setOnClickListener {
                Toast.makeText(view.context, currentCatJoke?.id.toString(), Toast.LENGTH_SHORT).show()
            }
        }

        /* Bind flower name and image. */
        fun bind(catJoke: CatJoke) {
            currentCatJoke = catJoke
            binding.catText.text = catJoke.joke
            val circularProgressDrawable = CircularProgressDrawable(itemView.context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()
            Glide
                .with(itemView.context)
                .load(catJoke.catImageUrl)
                .placeholder(circularProgressDrawable)
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