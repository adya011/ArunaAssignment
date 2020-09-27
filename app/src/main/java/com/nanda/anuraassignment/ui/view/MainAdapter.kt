package com.nanda.anuraassignment.ui.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nanda.anuraassignment.R
import com.nanda.anuraassignment.model.PostData
import kotlinx.android.synthetic.main.item_post.view.*

class MainAdapter(var items: List<PostData>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    fun setData(list: List<PostData>) {
        this.items = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.item_post, parent, false
        )
        return ViewHolder(v)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindView(items[position])

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: PostData) {
            with(itemView) {
                tvTitle.text = item.title
                tvDescription.text = item.body
            }
        }
    }


}