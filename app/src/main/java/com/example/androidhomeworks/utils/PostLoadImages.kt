package com.example.androidhomeworks.utils

import android.view.View
import com.bumptech.glide.Glide
import com.example.androidhomeworks.R
import com.example.androidhomeworks.databinding.PostBinding

fun PostBinding.loadImages(images: List<String>) {
    ivFirstImage.visibility = View.GONE
    ivSecondImage.visibility = View.GONE
    ivThirdImage.visibility = View.GONE

    when (images.size) {
        1 -> {
            ivFirstImage.visibility = View.VISIBLE
            Glide.with(ivFirstImage.context).load(images[0]).into(ivFirstImage)
        }

        2 -> {
            ivFirstImage.visibility = View.VISIBLE
            ivSecondImage.visibility = View.VISIBLE
            Glide.with(ivFirstImage.context).load(images[0]).into(ivFirstImage)
            Glide.with(ivSecondImage.context).load(images[1]).into(ivSecondImage)
        }

        3 -> {
            ivFirstImage.visibility = View.VISIBLE
            ivSecondImage.visibility = View.VISIBLE
            ivThirdImage.visibility = View.VISIBLE
            Glide.with(ivFirstImage.context).load(images[0]).into(ivFirstImage)
            Glide.with(ivSecondImage.context).load(images[1]).into(ivSecondImage)
            Glide.with(ivThirdImage.context).load(images[2]).into(ivThirdImage)
        }

        else -> {
            ivFirstImage.visibility = View.VISIBLE
            Glide.with(ivFirstImage.context).load(R.drawable.no_image).fitCenter().into(ivFirstImage)
        }
    }
}
