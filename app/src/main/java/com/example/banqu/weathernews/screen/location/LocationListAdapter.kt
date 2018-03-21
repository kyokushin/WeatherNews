package com.example.banqu.weathernews.screen.location

import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.banqu.weathernews.R

/**
 * Created by banqu on 2018/03/21.
 */
class LocationListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val locationList = arrayListOf<LocationListItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            when (LocationListItem.Type.values()[viewType]) {
                LocationListItem.Type.Prefecture ->
                    PrefectureViewHolder(LayoutInflater.from(parent.context)
                            .inflate(R.layout.item_prefecture_location_select, parent, false))
                LocationListItem.Type.City ->
                    CityViewHolder(LayoutInflater.from(parent.context)
                            .inflate(R.layout.item_city_location_select, parent, false))
            }


    override fun getItemViewType(position: Int) = locationList[position].type.ordinal

    override fun getItemCount() = locationList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (locationList[position].type) {
            LocationListItem.Type.Prefecture -> {
                val item = (locationList[position] as LocationListItem.PrefectureItem)
                val prefHolder = holder as PrefectureViewHolder
                prefHolder.name.text = item.prefecture.title
                holder.itemView.setOnClickListener {
                    prefHolder.name.text = item.prefecture.title
                    if (item.isOpen) {
                        item.isOpen = false
                        removeCityInPrefecture(position)
                    } else {
                        item.isOpen = true
                        addCityInPrefecture(position)
                    }
                }
            }
            LocationListItem.Type.City -> {
                val item = (locationList[position] as LocationListItem.CityItem)
                val cityHolder = holder as CityViewHolder
                cityHolder.name.text = item.city.title
                holder.itemView.setOnClickListener {
                    onSelectCity(position)
                }
            }
        }
    }

    fun onSelectCity(position: Int) {

    }

    fun addCityInPrefecture(position: Int) {
        locationList.addAll(position + 1,
                (locationList[position] as LocationListItem.PrefectureItem)
                        .prefecture.city
                        .map { LocationListItem.CityItem(it) }
        )
        notifyDataSetChanged()
    }

    fun removeCityInPrefecture(position: Int) {
        (locationList[position] as LocationListItem.PrefectureItem).prefecture.city
                .forEach {
                    locationList.removeAt(position + 1)
                }
        notifyDataSetChanged()
    }

    class PrefectureViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById(R.id.name) as AppCompatTextView
    }

    class CityViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById(R.id.name) as AppCompatTextView
    }
}