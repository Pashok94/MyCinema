package com.example.mycinema.ui.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mycinema.model.Film
import com.example.mycinema.R
import com.example.mycinema.databinding.FragmentMainRecyclerItemBinding

class MainFragmentAdapter(
    private var onItemClickListener: MainFragment.OnItemClickListener?
) : RecyclerView.Adapter<MainFragmentAdapter.MainViewHolder>() {
    var filmsData: ArrayList<Film> = arrayListOf()
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

        fun onBind(film: Film) {
            binding.filmTitle.text = film.title
            binding.filmDescription.text = film.description
            itemView.setOnClickListener {
                onItemClickListener?.onItemViewClick(film)
            }
        }
    }
}