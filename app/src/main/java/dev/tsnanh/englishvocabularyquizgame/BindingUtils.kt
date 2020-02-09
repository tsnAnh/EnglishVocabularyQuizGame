package dev.tsnanh.englishvocabularyquizgame

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.textview.MaterialTextView
import dev.tsnanh.englishvocabularyquizgame.database.PlayHistory
import dev.tsnanh.englishvocabularyquizgame.database.Vocabulary
import dev.tsnanh.englishvocabularyquizgame.ui.play.PlayViewModel

fun getPlaceholder(context: Context): Drawable {
    val circular = CircularProgressDrawable(context)
    circular.setColorSchemeColors(Color.parseColor(context.getString(R.string.primaryColor)))
    circular.strokeWidth = 10F
    circular.centerRadius = 50F
    circular.start()
    return circular
}

@BindingAdapter("image", "viewModel")
fun ImageView.setImage(vocabulary: Vocabulary?, viewModel: PlayViewModel) {
    vocabulary?.let {
        Glide
            .with(this.context)
            .load(this.context.getString(R.string.api_image_url) + vocabulary.image)
            .placeholder(getPlaceholder(this.context))
            .listener(object: RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    viewModel.startTimer()
                    return false
                }
            })
            .into(this)
    }
}

@BindingAdapter("textScoreResult")
fun MaterialTextView.setScoreText(playHistory: PlayHistory?) {
    playHistory?.let {
        text = it.correctQuestion.toString()
    }
}

@BindingAdapter("textTimeResult")
fun MaterialTextView.setTimeText(playHistory: PlayHistory?) {
    playHistory?.let {
        text = it.timeEnd
    }
}

@BindingAdapter("imageScoreRating")
fun ImageView.setImageRating(playHistory: PlayHistory?) {
    playHistory?.let {
        setImageResource(
            when (playHistory.scoreRating) {
                0 -> R.drawable.ic_sentiment_very_dissatisfied_black_24dp
                1 -> R.drawable.ic_sentiment_dissatisfied_black_24dp
                2 -> R.drawable.ic_sentiment_neutral_black_24dp
                3 -> R.drawable.ic_sentiment_satisfied_black_24dp
                4 -> R.drawable.ic_sentiment_very_satisfied_black_24dp
                else -> R.drawable.ic_sentiment_neutral_black_24dp
            }
        )
    }
}