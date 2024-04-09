package com.ntxq.countryapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ntxq.countryapp.databinding.ItemCountryBinding
import com.ntxq.countryapp.model.Country
import com.ntxq.countryapp.util.loadImageUrl

class ListCountryAdapter(
    private val onItemCountryCLick: (Country) -> Unit
) :
    ListAdapter<Country, ListCountryAdapter.CountryViewHolder>(DIFF_ITEM), Filterable {
    companion object {
        private val DIFF_ITEM = object : DiffUtil.ItemCallback<Country>() {
            override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
                return oldItem.name.official == newItem.name.official
            }

            override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
                return oldItem.name.common == newItem.name.common
                        && oldItem.flag.url == newItem.flag.url
            }

        }
    }

    private var listData = ArrayList<Country>()

    class CountryViewHolder(
        private val binding: ItemCountryBinding,
        private val onItemCountryCLick: (Country) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(country: Country) {
            binding.imageFlag.loadImageUrl(country.flag.url)
            binding.textName.text = country.name.common
            binding.root.setOnClickListener {
                onItemCountryCLick.invoke(country)
            }
        }
    }

    fun setData(list: List<Country>) {
        listData.clear()
        listData.addAll(list)
        submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding, onItemCountryCLick)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bindView(currentList[position])
    }

    override fun getFilter(): Filter {
        return customFilter
    }

    private val customFilter = object : Filter() {
        override fun performFiltering(text: CharSequence?): FilterResults {
            val filteredList = if (text.isNullOrEmpty()) {
                listData
            } else {
                listData.filter {
                    it.name.common.lowercase().startsWith(text.toString().lowercase())
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, filterResults: FilterResults?) {
            submitList(filterResults?.values as ArrayList<Country>)
        }

    }
}