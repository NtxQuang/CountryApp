package com.ntxq.countryapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ntxq.countryapp.databinding.ItemCountryBinding
import com.ntxq.countryapp.model.SavedCountry
import com.ntxq.countryapp.util.loadImageUrl

class SavedCountryAdapter(
    private val onItemCountryCLick: (SavedCountry) -> Unit,
    private val onButtonDeleteClick: (SavedCountry) -> Unit
) :
    ListAdapter<SavedCountry, SavedCountryAdapter.CountryViewHolder>(DIFF_ITEM) {
    companion object {
        private val DIFF_ITEM = object : DiffUtil.ItemCallback<SavedCountry>() {
            override fun areItemsTheSame(oldItem: SavedCountry, newItem: SavedCountry): Boolean {
                return oldItem.officialName == newItem.officialName
            }

            override fun areContentsTheSame(oldItem: SavedCountry, newItem: SavedCountry): Boolean {
                return oldItem.commonName == newItem.commonName
                        && oldItem.flagUrl == newItem.flagUrl
            }

        }
    }

    class CountryViewHolder(
        private val binding: ItemCountryBinding,
        private val onItemCountryCLick: (SavedCountry) -> Unit,
        private val onButtonDeleteClick: (SavedCountry) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(savedCountry: SavedCountry) {
            binding.imageFlag.loadImageUrl(savedCountry.flagUrl)
            binding.textName.text = savedCountry.commonName
            binding.root.setOnClickListener {
                onItemCountryCLick.invoke(savedCountry)
            }
            binding.buttonDelete.isVisible = true
            binding.buttonDelete.setOnClickListener {
                onButtonDeleteClick.invoke(savedCountry)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding, onItemCountryCLick, onButtonDeleteClick)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bindView(currentList[position])
    }
}