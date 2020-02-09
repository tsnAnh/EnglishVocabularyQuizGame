package dev.tsnanh.englishvocabularyquizgame.ui.settings


import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import dev.tsnanh.englishvocabularyquizgame.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(
            R.xml.preferences_xml, getString(R.string.shared_preferences_key)
        )
    }


}
