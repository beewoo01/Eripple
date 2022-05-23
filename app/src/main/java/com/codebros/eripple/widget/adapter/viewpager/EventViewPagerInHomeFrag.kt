package com.codebros.eripple.widget.adapter.viewpager

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codebros.eripple.R
import com.codebros.eripple.data.url.DefaultUrl
import com.codebros.eripple.databinding.ViewholderImageBinding
import com.codebros.eripple.extention.load
import com.codebros.eripple.model.event.EventWithThumbnail
import com.codebros.eripple.widget.adapter.listener.event.EventWithThumbnailListener

class EventViewPagerInHomeFrag(
    private val list: MutableList<EventWithThumbnail>,
    private val listener : EventWithThumbnailListener
) : ListAdapter<EventWithThumbnail, EventViewPagerInHomeFrag.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ViewholderImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun onBind(model : EventWithThumbnail) = with(binding) {
                //Glide.with(thumbnailImv.context).load("http://raon-soft.com/imagefile/nbiz/image/1634184234249.JPG").into(thumbnailImv)
                thumbnailImv.load(DefaultUrl.SAMPLE_IMAGE_URL + model.event_image_url)
                root.setOnClickListener {
                    listener.onClickItem(model)
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ViewholderImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<EventWithThumbnail>() {
            override fun areItemsTheSame(
                oldItem: EventWithThumbnail,
                newItem: EventWithThumbnail
            ): Boolean {
                return oldItem.event_idx == newItem.event_idx
            }

            override fun areContentsTheSame(
                oldItem: EventWithThumbnail,
                newItem: EventWithThumbnail
            ): Boolean {
                return  oldItem == newItem
            }

        }
    }
}