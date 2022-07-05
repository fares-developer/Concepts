package com.example.learning.ui.first.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.learning.data.model.Poster
import com.example.learning.databinding.PostItemViewBinding

//ListAdapter avisa al daptador cuando se actualiza la lista de items
class PosterAdapter(val clickListener: PosterListener) :
    ListAdapter<Poster, PosterAdapter.ViewHolder>(PosterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!,clickListener)
    }


    class ViewHolder private constructor(itemView: PostItemViewBinding) :
        RecyclerView.ViewHolder(itemView.root) {

        fun bind(poster: Poster, clickListener: PosterListener) {
            binding.clickListener = clickListener
            binding.poster = poster
            binding.executePendingBindings()
        }

        companion object { //Para que pueda acceder cualquier ViewHolder

            private lateinit var binding: PostItemViewBinding

            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                binding = PostItemViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

/**
 * Esta clase es para trabajar con DiffUtil
 */
class PosterDiffCallback : DiffUtil.ItemCallback<Poster>() {

    override fun areItemsTheSame(oldItem: Poster, newItem: Poster): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Poster, newItem: Poster): Boolean {
        return oldItem == newItem
    }
}

class PosterListener(val clickListener: (id: Long) -> Unit) {

    fun onClick(poster: Poster) = clickListener(poster.id)
}