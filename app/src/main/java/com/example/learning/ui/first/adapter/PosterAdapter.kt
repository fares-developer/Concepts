package com.example.learning.ui.first.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.learning.R
import com.example.learning.data.local.Poster
import com.example.learning.databinding.PostItemViewBinding

class PosterAdapter : RecyclerView.Adapter<PosterAdapter.ViewHolder>() {

    var posters = listOf<Poster>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = posters[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = posters.size


    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = PostItemViewBinding.bind(itemView)

        fun bind(poster: Poster) {

            binding.postAuthor.text = poster.author
            if (poster.postPath != null)
                Glide.with(itemView.context).load(poster.postPath).into(binding.postImage)
            else
                binding.postImage.setImageResource(R.mipmap.ic_launcher)

            binding.postDownloads.text = poster.postDownloads.toString()
        }

        companion object { //Esto es así para que puede acceder cualquier ViewHolder
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                return ViewHolder(
                    layoutInflater.inflate(R.layout.post_item_view, parent, false)
                )
            }
        }
    }
}