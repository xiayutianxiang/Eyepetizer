package com.pax.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.pax.data.Movie
import com.pax.mylibrary.R
import com.squareup.picasso.Picasso

class MoviePagedListAdapter{
//分析点2
    /*(context: Context) :
    PagedListAdapter<Movie, MoviePagedListAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    private var context: Context? = context

    companion object {
        //分析点3
        //差分，只更新需要更新的元素，而不是整个刷新数据源
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<Movie> =
            object : DiffUtil.ItemCallback<Movie>() {

                //调用以检查两个对象是否代表同一个项目。
                //如果两个项目代表相同的对象，则为真，如果它们不同，则为假。
                override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                    return oldItem === newItem
                }

                //调用以检查两个项目是否具有相同的数据。
                //此信息用于检测项目的内容是否已更改。
                //如果项目的内容相同则为真，如果不同则为假。
                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                    return oldItem == newItem
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)

        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)!!
        movie.let {
            Picasso.get().load(it.cover)
                .into(holder.imageView)
            if (it.title!!.length >= 8) {
                movie.title = movie.title!!.substring(0, 7)
            }
            holder.textViewTitle!!.text = movie.title
            holder.textViewRate!!.text = movie.rate
        }
    }


    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView? = null
        var textViewTitle: TextView? = null
        var textViewRate: TextView? = null

        init {
            imageView = itemView.findViewById(R.id.imageView)
            textViewTitle = itemView.findViewById(R.id.textViewTitle)
            textViewRate = itemView.findViewById(R.id.textViewRate)
        }
    }*/
}