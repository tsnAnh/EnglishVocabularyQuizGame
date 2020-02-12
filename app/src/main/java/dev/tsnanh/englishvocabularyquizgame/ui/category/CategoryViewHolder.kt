package dev.tsnanh.englishvocabularyquizgame.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.tsnanh.englishvocabularyquizgame.database.Category
import dev.tsnanh.englishvocabularyquizgame.databinding.ItemListCategoryBinding

class CategoryViewHolder(
    private val binding: ItemListCategoryBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(category: Category, listener: CategoryListener) {
        binding.category = category
        binding.clickListener = listener
        binding.imageView = binding.image
        binding.image.transitionName = category.intro_img
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): CategoryViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemListCategoryBinding
                .inflate(inflater, parent, false)
            return CategoryViewHolder(binding)
        }
    }
}