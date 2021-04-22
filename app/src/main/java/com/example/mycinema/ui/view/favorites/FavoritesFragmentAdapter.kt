package com.example.mycinema.ui.view.favorites

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mycinema.R
import com.example.mycinema.databinding.FragmentMainRecyclerItemBinding
import com.example.mycinema.model.Result

class FavoritesFragmentAdapter :
    RecyclerView.Adapter<FavoritesFragmentAdapter.FavoritesViewHolder>() {
    var favoritesFilms: List<Result> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        return FavoritesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_main_recycler_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.onBind(favoritesFilms[position])
    }

    override fun getItemCount(): Int {
        return favoritesFilms.size
    }

    inner class FavoritesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = FragmentMainRecyclerItemBinding.bind(itemView)
        @SuppressLint("SetTextI18n")
        fun onBind(film: Result) {
            with(binding){
                filmTitle.text = film.title
                filmParam.text = "Release ${film.release_date}\nRating ${film.vote_average}"
            }
        }
    }
}