package com.setung.android.newsapp.Fragment.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.setung.android.newsapp.Model.Article
import com.setung.android.newsapp.R

class RecyclerViewAdapter(onClick: View.OnClickListener,onLongClick:View.OnLongClickListener) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var myDataset: ArrayList<Article> = ArrayList()
    var onClickNewsListener: View.OnClickListener = onClick
    var onNewsLongClickListener : View.OnLongClickListener = onLongClick

    class MyViewHolder(val v: View, onClickNewsListener: View.OnClickListener, onNewsLongClickListener : View.OnLongClickListener) :
        RecyclerView.ViewHolder(v) {
        var tv_title: TextView = v.findViewById(R.id.item_tv_title)
        var tv_contents: TextView = v.findViewById(R.id.item_tv_contents)
        var imgView: ImageView = v.findViewById(R.id.item_iv_image)

        init {
            v.isClickable = true
            v.isEnabled = true
            v.setOnClickListener(onClickNewsListener)
            v.setOnLongClickListener(onNewsLongClickListener)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_item, parent, false) as View
        return MyViewHolder(v, onClickNewsListener,onNewsLongClickListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tv_title.text = myDataset[position].title
        holder.tv_contents.text = myDataset[position].description
        holder.v.tag = position

        if (myDataset[position].urlToImage != null)
            Glide.with(holder.v).load(myDataset[position].urlToImage).into(holder.imgView)
    }

    override fun getItemCount() = myDataset.size

}
