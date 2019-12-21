package com.sundaymobility.githubusers.ui.view_binding_adapters

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.github.chrisbanes.photoview.PhotoView
import com.sundaymobility.githubusers.R


class DetailActivityBindingAdapters {

    companion object {
        @BindingAdapter("detail_avatar")
        @JvmStatic
        fun loadImage(view: PhotoView, imageUrl: String) {

            Glide.with(view.context)
                .load(imageUrl)
                .placeholder(R.drawable.ic_empty_avatar)
                .into(view)


        }
    }


}