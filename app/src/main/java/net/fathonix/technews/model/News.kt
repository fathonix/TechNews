package net.fathonix.technews.model

import android.content.Context
import android.os.Parcelable
import android.widget.Toast
import kotlinx.parcelize.Parcelize
import org.json.JSONArray

@Parcelize
data class News(
	val thumb: String? = null,
	val author: String? = null,
	val time: String? = null,
	val title: String? = null,
	val desc: String? = null,
) : Parcelable {

    companion object {
        fun parseJSON(context: Context, json: String): ArrayList<News> {
            var newsJSON: JSONArray
            try {
                newsJSON = JSONArray(json)
            } catch (e: Exception) {
                newsJSON = JSONArray()
                Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
            }

            val newsArray = ArrayList<News>()
            for (i in 0 until newsJSON.length()) {
                val currentNews = newsJSON.getJSONObject(i)
                val descArray = currentNews.getJSONArray("desc")

                var desc = ""
                for (i in 0 until descArray.length()) {
                    desc += "${descArray.getString(i)}\n\n"
                }

                newsArray.add(
                    News(
                        thumb = currentNews.getString("thumb"),
                        author = currentNews.getString("author"),
                        time = currentNews.getString("time"),
                        title = currentNews.getString("title"),
                        desc = desc
                    )
                )
            }

            return newsArray
        }
    }

}
