package com.example.mycinema.ui.view.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mycinema.model.Result
import com.example.mycinema.R
import com.example.mycinema.databinding.FragmentMainRecyclerItemBinding
import com.squareup.picasso.Picasso

class MainFragmentAdapter(
private var onItemClickListener:((result: Result) -> Unit)?
) : RecyclerView.Adapter<MainFragmentAdapter.MainViewHolder>() {
    var filmsData: List<Result> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_main_recycler_item, parent, false) as View
        )
    }

    override fun onViewRecycled(holder: MainViewHolder) {
        super.onViewRecycled(holder)
        holder.onUnbind()
    }

    override fun getItemCount(): Int {
        return filmsData.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.onBind(filmsData[position])
    }

    fun removeListener(){
        onItemClickListener = null
    }

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = FragmentMainRecyclerItemBinding.bind(itemView)

        @SuppressLint("SetTextI18n")
        fun onBind(result: Result) {
            binding.filmTitle.text = result.title
            binding.filmParam.text = "Release ${result.release_date}\nRating ${result.vote_average}"
            loadAndSetPoster(result.poster_path)
            itemView.setOnClickListener {
                onItemClickListener?.invoke(result)
            }
        }

        private fun loadAndSetPoster(path: String){
            Picasso
                .get()
                .load("https://image.tmdb.org/t/p/w500$path")
                .into(binding.filmPoster)
        }

        fun onUnbind(){
            Picasso.get().cancelRequest(binding.filmPoster)
        }
    }
}