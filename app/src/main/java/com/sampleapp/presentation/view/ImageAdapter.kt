package com.sampleapp.presentation.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.sampleapp.R
import com.sampleapp.data.models.ImageModel
import com.squareup.picasso.Picasso

class ImageAdapter(private val context : Context, private val imageList: List<ImageModel>) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    private var onClickListener: OnClickListener? = null

    inner class ImageViewHolder(iteView: View) : ViewHolder(iteView) {
        private var ivImage: ImageView = itemView.findViewById(R.id.iv_image)

        fun bind(imageModel: ImageModel) {
            Picasso.with(context).load(imageModel.thumbnailUrl)
                .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(ivImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_images, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(imageList[position])
        holder.itemView.setOnClickListener { onClickListener?.onClick(position, imageList[position]) }
    }

    fun setOnClickListener(onClickListener: OnClickListener?) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onClick(position: Int, model: ImageModel?)
    }
}