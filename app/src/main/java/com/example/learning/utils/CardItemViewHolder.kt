package com.example.learning.utils

import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.learning.R
import com.example.learning.data.local.Poster
import com.example.learning.databinding.PostItemViewBinding

class CardItemViewHolder(val card:CardView):RecyclerView.ViewHolder(card) {

    private val binding = PostItemViewBinding.bind(card)

    fun render(poster: Poster) {

        binding.postAuthor.text = poster.author
        if(poster.postPath != null)
            Glide.with(itemView.context).load(poster.postPath).into(binding.postImage)
        else
            binding.postImage.setImageResource(R.mipmap.ic_launcher)

        binding.postDownloads.text = poster.postDownloads.toString()
    }
}