package dev.tsnanh.englishvocabularyquizgame.ui.high_score

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.tsnanh.englishvocabularyquizgame.database.PlayHistory

class HighScoreAdapter : ListAdapter<PlayHistory, ViewHolder>(PlayHistoryDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class PlayHistoryDiffCallback : DiffUtil.ItemCallback<PlayHistory>() {
    override fun areItemsTheSame(oldItem: PlayHistory, newItem: PlayHistory): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: PlayHistory, newItem: PlayHistory): Boolean {
        return oldItem.id == newItem.id
    }
}