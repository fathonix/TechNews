package net.fathonix.technews.util

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import net.fathonix.technews.databinding.NewsItemBinding
import net.fathonix.technews.model.News
import net.fathonix.technews.ui.DetailActivity

class NewsAdapter(private val list: ArrayList<News>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder = NewsViewHolder(
        NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        list[position].also { news ->
            holder.binding.apply {
                tvTitle.text = news.title
                tvTime.text = news.time
                tvAuthor.text = news.author
                tvDesc.text = news.desc
                Glide.with(root)
                    .load(news.thumb)
                    .into(imgBanner)
                root.setOnClickListener {
                    root.context.startActivity(
                        Intent(root.context, DetailActivity::class.java)
                            .putExtra(DetailActivity.EXTRA_DATA, news)
                    )
                }
            }
        }
    }

    override fun getItemCount(): Int = list.size
}
