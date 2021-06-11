package com.jsz.collapsingtoolbardemo

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.jsz.collapsingtoolbardemo.databinding.ActivityScrollingBinding

class ScrollingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScrollingBinding

    private var textToggle = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScrollingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        binding.toolbarLayout.title = title
//        binding.content.button.setOnClickListener { toggleText() }
        binding.content.buttonTop.setOnClickListener { toggleText() }

        binding.toolbarLayout.adoptScrollingFlagsBasedOn(binding.content.nestedScrollView)
    }

    private fun toggleText() {
        textToggle += 1
        when (textToggle) {
            0 -> {
                binding.content.textView.setText(R.string.large_text)
            }
            1 -> {
                binding.content.textView.setText(R.string.medium_text)
            }
            else -> {
                binding.content.textView.setText(R.string.small_text)
                textToggle = -1
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
