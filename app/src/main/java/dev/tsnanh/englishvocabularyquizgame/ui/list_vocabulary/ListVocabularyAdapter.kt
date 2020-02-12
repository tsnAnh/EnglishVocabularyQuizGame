package dev.tsnanh.englishvocabularyquizgame.ui.list_vocabulary

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import dev.tsnanh.englishvocabularyquizgame.database.Vocabulary

class ListVocabularyAdapter(
    private val clickListener: ListVocabularyClickListener
) : PagedListAdapter<Vocabulary, ListVocabularyViewHolder>(ListVocabularyDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListVocabularyViewHolder.from(parent)

    override fun onBindViewHolder(holder: ListVocabularyViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}

class ListVocabularyClickListener(val clickListener: (vocabularyID: Long) -> Unit) {
    fun onClick(vocabularyID: Long) = clickListener(vocabularyID)
}

class ListVocabularyDiffCallback : DiffUtil.ItemCallback<Vocabulary>() {
    override fun areItemsTheSame(oldItem: Vocabulary, newItem: Vocabulary): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Vocabulary, newItem: Vocabulary): Boolean {
        return oldItem.id == newItem.id
    }
}