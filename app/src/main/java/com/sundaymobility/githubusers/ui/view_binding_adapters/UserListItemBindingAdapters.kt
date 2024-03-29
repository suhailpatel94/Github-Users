package com.sundaymobility.githubusers.ui.view_binding_adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.sundaymobility.githubusers.R


class UserListItemBindingAdapters {

    companion object {
        @BindingAdapter("avatar")
        @JvmStatic
        fun loadImage(view: ImageView, imageUrl: String) {
            Glide.with(view.context)
                .load(imageUrl)
                .placeholder(R.drawable.ic_empty_avatar)
                .into(view)
        }
    }


}