package io.tianb0.bullseye

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.tianb0.bullseye.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = getString(R.string.about_page_title)

        // it's because we only created it in landscape xml for the screen
        // we can avoid it by put it in the default xml
        binding.aboutBackButton?.setOnClickListener {
            finish()
        }
    }
}