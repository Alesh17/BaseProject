package com.baseproject.util.view

import android.widget.ImageView
import com.baseproject.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory as DCFF

val glideFactory: DCFF = DCFF.Builder().setCrossFadeEnabled(true).build()
val glideTransitionOption = DrawableTransitionOptions.withCrossFade(glideFactory)
val glideCircleTransformOption = RequestOptions().apply(RequestOptions.circleCropTransform())

private const val FAKE_AVATAR_URI = "https://images.generated.photos/JItfQIJWkeuwjHGGJhprVbfwntPWINwPmXmU7sscwaI/rs:fit:256:256/Z3M6Ly9nZW5lcmF0/ZWQtcGhvdG9zL3Yz/XzAyNjY3NzQuanBn.jpg"

fun ImageView.loadFakeAvatar(uri: String = FAKE_AVATAR_URI) {
    setCircleImage(uri)
}

fun ImageView.setCircleImage(uri: String?) {
    Glide.with(this)
        .load(uri)
        .apply(glideCircleTransformOption)
        .error(R.drawable.ic_avatar_placeholder)
        .fallback(R.drawable.ic_avatar_placeholder)
        .placeholder(R.drawable.ic_avatar_placeholder)
        .transition(glideTransitionOption)
        .into(this)
}

fun ImageView.setImageWithRadius(uri: String?) {
    val radius = context.resources.getDimension(R.dimen.common_radius).toInt()
    Glide.with(context)
        .load(uri)
        .transform(CenterCrop(), RoundedCorners(radius))
        .transition(glideTransitionOption)
        .into(this)
}