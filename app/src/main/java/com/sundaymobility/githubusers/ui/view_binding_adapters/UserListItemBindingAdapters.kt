package com.sundaymobility.githubusers.ui.view_binding_adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


class UserListItemBindingAdapters {

    companion object {
        @BindingAdapter("avatar")
        @JvmStatic
        fun loadImage(view: ImageView, imageUrl: String) {
            Glide.with(view.context)
                .load(imageUrl)
                .into(view)
        }
    }


}