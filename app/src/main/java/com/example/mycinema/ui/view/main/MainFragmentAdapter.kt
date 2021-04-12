package com.example.mycinema.ui.view.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mycinema.model.Result
import com.example.mycinema.R
import com.example.mycinema.databinding.FragmentMainRecyclerItemBinding

class MainFragmentAdapter(
private var onItemClickListener:((result: Result) -> Unit)?
) : RecyclerView.Adapter<MainFragmentAdapter.MainViewHolder>() {
    var filmsData: ArrayList<Result> = arrayListOf()
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

        @SuppressLint("SetTextI18n")
        fun onBind(result: Result) {
            binding.filmTitle.text = result.title
            binding.filmParam.text = "Release ${result.release_date}\nRating ${result.vote_average}"
            itemView.setOnClickListener {
                onItemClickListener?.invoke(result)
            }
        }
    }
}