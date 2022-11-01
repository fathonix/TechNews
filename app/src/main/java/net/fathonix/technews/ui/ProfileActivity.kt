package net.fathonix.technews.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import net.fathonix.technews.R
import net.fathonix.technews.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.profile)
        binding.apply {
            setContentView(root)
            btnEmail.setOnClickListener {
                openUri("mailto:${getString(R.string.email)}")
            }
            btnUsername.setOnClickListener {
                openUri("https://www.dicoding.com/users/${getString(R.string.username)}")
            }
            btnGithubName.setOnClickListener {
                openUri("https://github.com/${getString(R.string.username)}")
            }
            btnIgName.setOnClickListener {
                openUri("https://instagram.com/${getString(R.string.ig_name)}")
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun openUri(uriString: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(uriString)))
    }
}