package com.supinfo.webserviceapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.supinfo.webserviceapp.R
import com.supinfo.webserviceapp.model.TvShow

class TVShowAdapter(private val tvShows: List<TvShow>) : RecyclerView.Adapter<TVShowAdapter.TVShowHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowHolder {

        return TVShowHolder(LayoutInflater.from(parent.context).inflate(R.layout.tv_show_item, parent, false))

    }

    override fun onBindViewHolder(holder: TVShowHolder, position: Int) {
        holder.bindView(tvShows[position])
    }

    override fun getItemCount(): Int = tvShows.size


    class TVShowHolder(private val tvShowView: View): RecyclerView.ViewHolder(tvShowView) {

        private val tvShowImage : ImageView = itemView.findViewById(R.id.tvShowImage)
        private val textNameLinear : TextView = itemView.findViewById(R.id.textNameLinear)
        private val textNetworkAndCountryLinear : TextView = itemView.findViewById(R.id.textNetworkAndCountryLinear)
        private val textStartDateLinear : TextView = itemView.findViewById(R.id.textStartDateLinear)
        private val textStatusLinear : TextView = itemView.findViewById(R.id.textStatusLinear)

        @SuppressLint("SetTextI18n")
        fun bindView(tvShow: TvShow){
            Glide.with(tvShowView.context)
                .load(tvShow.imageTvShowUrl)
                .into(tvShowImage)

            textNameLinear.text = tvShow.name
            textNetworkAndCountryLinear.text = "${tvShow.network} (${tvShow.country})"
            textStartDateLinear.text = "Started on ${tvShow.startDate}"
            textStatusLinear.text = tvShow.status

        }
    }
}