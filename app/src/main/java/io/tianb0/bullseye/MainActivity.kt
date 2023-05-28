package io.tianb0.bullseye

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AlertDialog
import io.tianb0.bullseye.databinding.ActivityMainBinding
import kotlin.math.abs
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var sliderValue = 0
    private var targetValue = Random.nextInt(1, 100)
    private var totalScore = 0
    private var round = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        reset()

        binding.hitMeButton.setOnClickListener {
            val point = calculatePoint()
            showResultDialog(point)
            totalScore += point
            binding.scoreTextView.text = totalScore.toString()
            round += 1
            binding.roundTextView.text = round.toString()
        }

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                sliderValue = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) { }
            override fun onStopTrackingTouch(seekBar: SeekBar?) { }
        })

        binding.startOverButton.setOnClickListener {
            reset()
        }

//        supportActionBar?.hide() // use fullscreen theme for this activity instead
    }

    private fun showResultDialog(currentScore: Int) {
        val dialogTitle = getString(R.string.result_dialog_title)
        val dialogMessage = getString(R.string.result_dialog_message, sliderValue, currentScore)

        AlertDialog.Builder(this)
            .setTitle(dialogTitle)
            .setMessage(dialogMessage)
            .setPositiveButton(R.string.result_dialog_button_text) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun calculatePoint(): Int {
        val fullScore = 100
        return fullScore - abs(targetValue - sliderValue)
    }

    private fun reset() {
        sliderValue = 0
        totalScore = 0
        round = 0
        targetValue = Random.nextInt(1, 100)

        binding.scoreTextView.text = totalScore.toString()
        binding.roundTextView.text = round.toString()
        binding.targetTextView.text = targetValue.toString()
        binding.seekBar.progress = sliderValue
    }
}