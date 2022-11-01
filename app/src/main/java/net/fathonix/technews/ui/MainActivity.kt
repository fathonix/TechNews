package net.fathonix.technews.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import net.fathonix.technews.R
import net.fathonix.technews.databinding.ActivityMainBinding
import net.fathonix.technews.model.News
import net.fathonix.technews.util.NewsAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var doubleBackToExitOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.apply {
            setContentView(root)
            rvNews.adapter = NewsAdapter(
                News.parseJSON(this@MainActivity, readFromAsset("tlm-tech.json"))
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_profile -> startActivity(
                Intent(this, ProfileActivity::class.java)
            )
            R.id.item_read_more -> startActivity(
                Intent(Intent.ACTION_VIEW).setData(Uri.parse(
                    "https://thelazy.media"
                ))
            )
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (doubleBackToExitOnce) {
            super.onBackPressed()
            return
        }
        doubleBackToExitOnce = true
        Toast.makeText(this, getString(R.string.confirm_exit), Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed({
            kotlin.run { doubleBackToExitOnce = false }
        }, 2000)
    }

    private fun readFromAsset(filename: String): String =
        try {
            assets.open(filename).bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            ""
        }
}