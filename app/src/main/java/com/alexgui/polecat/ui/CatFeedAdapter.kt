package com.alexgui.polecat.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexgui.polecat.ApiEndPoint
import com.alexgui.polecat.R
import com.alexgui.polecat.databinding.CatfeedItemBinding
import com.alexgui.polecat.model.data.CatFeed
import com.squareup.picasso.Picasso

class CatFeedAdapter(private val listener: CatFeedItemListener) : RecyclerView.Adapter<CatFeedViewHolder>(){
    interface CatFeedItemListener {
        fun onClickedCatFeed(catFeed: CatFeed)
    }

    private val items = ArrayList<CatFeed>()

    fun setItems(items: ArrayList<CatFeed>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatFeedViewHolder {
        val binding: CatfeedItemBinding = CatfeedItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatFeedViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CatFeedViewHolder, position: Int) = holder.bind(items[position])

}

class CatFeedViewHolder(private val itemBinding: CatfeedItemBinding, private val listener: CatFeedAdapter.CatFeedItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnLongClickListener {

    private lateinit var catFeed: CatFeed

    init {
        itemBinding.root.setOnLongClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: CatFeed) {
        this.catFeed = item
        itemBinding.catFact.text =  "${item.fact}"
        Picasso.get().load(ApiEndPoint.BASE_URL+"${item.image}").error(R.drawable.ic_launcher_background)
            .into(itemBinding.catImage)
    }

    override fun onLongClick(p0: View?): Boolean {

        listener.onClickedCatFeed(this.catFeed)
        return true
    }
}