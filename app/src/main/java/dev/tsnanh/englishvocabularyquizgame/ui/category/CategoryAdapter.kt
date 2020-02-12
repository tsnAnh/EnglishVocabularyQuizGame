package dev.tsnanh.englishvocabularyquizgame.ui.category

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import dev.tsnanh.englishvocabularyquizgame.database.Category

class CategoryAdapter(
    private val listener: CategoryListener
) : ListAdapter<Category, CategoryViewHolder>(CategoryDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CategoryViewHolder.from(parent)

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }
}

class CategoryListener(val listener: (category: Category, imageView: ImageView) -> Unit) {
    fun onClick(category: Category, imageView: ImageView) = listener(category, imageView)
}

class CategoryDiffCallBack : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.id == newItem.id
    }
}