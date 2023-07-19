package com.mibaldi.monumentoszaragoza.ui.common

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.ImageView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.mibaldi.monumentoszaragoza.R
import com.mibaldi.monumentoszaragoza.domain.MyError
import com.mibaldi.monumentoszaragoza.ui.detail.DetailActivity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

fun <T> LifecycleOwner.launchAndCollect(
    flow: Flow<T>,
    state: Lifecycle.State = Lifecycle.State.STARTED,
    body: (T) -> Unit
) {
    lifecycleScope.launch {
        this@launchAndCollect.repeatOnLifecycle(state) {
            flow.collect(body)
        }
    }
}

fun ImageView.loadUrl(url: String) {
    Glide.with(context)
        .load(url)
        .error(R.drawable.no_image_available)
        .into(this)
}
fun Context.errorToString(error: MyError) = when (error) {
    MyError.Connectivity -> getString(R.string.connectivity_error)
    is MyError.Server -> getString(R.string.server_error) + error.code
    is MyError.Unknown -> getString(R.string.unknown_error) + error.message
}
fun Activity.goToDetail(monumentoId: Int) {
    val intent = Intent(this, DetailActivity::class.java)
    intent.putExtra("monumentoId", monumentoId)
    startActivity(intent)
}