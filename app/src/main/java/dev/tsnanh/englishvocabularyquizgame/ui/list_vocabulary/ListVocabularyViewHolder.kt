package dev.tsnanh.englishvocabularyquizgame.ui.list_vocabulary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.tsnanh.englishvocabularyquizgame.database.Vocabulary
import dev.tsnanh.englishvocabularyquizgame.databinding.ItemListVocabularyBinding

class ListVocabularyViewHolder(
    private val binding: ItemListVocabularyBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(vocabulary: Vocabulary?, clickListener: ListVocabularyClickListener) {
        binding.vocabulary = vocabulary
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): ListVocabularyViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemListVocabularyBinding
                .inflate(inflater, parent, false)
            return ListVocabularyViewHolder(binding)
        }
    }
}