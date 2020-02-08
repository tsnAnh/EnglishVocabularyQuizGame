package dev.tsnanh.englishvocabularyquizgame.ui.high_score

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.tsnanh.englishvocabularyquizgame.R
import dev.tsnanh.englishvocabularyquizgame.database.PlayHistory
import dev.tsnanh.englishvocabularyquizgame.databinding.ItemListHighScoreBinding

class ViewHolder(private val binding: ItemListHighScoreBinding)
    : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(parent: ViewGroup): ViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding =
                ItemListHighScoreBinding.inflate(inflater, parent,false)
            return ViewHolder(binding)
        }
    }

    fun bind(playHistory: PlayHistory) {
        binding.playHistory = playHistory
        binding.executePendingBindings()
    }
}