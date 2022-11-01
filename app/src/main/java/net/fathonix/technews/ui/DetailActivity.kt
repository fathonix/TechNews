package net.fathonix.technews.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import net.fathonix.technews.databinding.ActivityDetailBinding
import net.fathonix.technews.model.News

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "data"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val data = intent.getParcelableExtra<News>(EXTRA_DATA) ?: News()
        data.apply {
            binding.apply {
                setContentView(root)
                tvTitle.text = title
                tvTime.text = time
                tvAuthor.text = author
                tvDesc.text = desc
                Glide.with(this@DetailActivity)
                    .load(thumb)
                    .into(detailImg)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}