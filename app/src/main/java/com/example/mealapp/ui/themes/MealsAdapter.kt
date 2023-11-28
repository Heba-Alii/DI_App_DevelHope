package com.example.mealapp.ui.themes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mealapp.databinding.CategoryItemBinding
import com.example.mealapp.domain.model.Categories

class MealsAdapter() : ListAdapter<Categories, MealsAdapter.ViewHolder>(CategoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val itemBinding: CategoryItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(categories: Categories) {
            itemBinding.item = categories
//            itemBinding.apply {
//                categoryNameTv.text = categories.strCategory
//                categoryDescTv.text = categories.strCategoryDescription
//                Glide.with(itemBinding.root.context)
//                    .load(categories.strCategoryThumb)
//                    .into(itemBinding.mealImage)
//            }
        }
    }
}

class CategoryDiffCallback : DiffUtil.ItemCallback<Categories>() {
    override fun areItemsTheSame(oldItem: Categories, newItem: Categories): Boolean {
        return oldItem.idCategory == newItem.idCategory
    }

    override fun areContentsTheSame(oldItem: Categories, newItem: Categories): Boolean {
        return oldItem == newItem
    }

}

