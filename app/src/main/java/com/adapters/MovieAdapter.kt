package com.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.model.Result
import com.moviehub.R

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgView: ImageView = itemView.findViewById(R.id.item_movie_poster)

        fun bind(imageUrl: String) {
            Log.i("function", "binding called")
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w500${imageUrl}")
                .centerCrop()
                .into(imgView)
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout
                        .item_movie, parent, false
                )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val result = differ.currentList[position]
        holder.itemView.apply {
            holder.bind(result.poster_path)

        }
        holder.itemView.setOnClickListener {
                onItemClickListener?.let { it(result) }
        }
    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Result) -> Unit)? = null

    fun setItemClickListener(listener: (Result) -> Unit) {
        onItemClickListener = listener
    }
}