package com.mibaldi.monumentoszaragoza.ui.common

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.mibaldi.monumentoszaragoza.domain.Monumento
import com.mibaldi.monumentoszaragoza.ui.detail.MonumentoDetailInfoView

@BindingAdapter("url")
fun ImageView.bindUrl(url: String?) {
    if (url != null) loadUrl(url)
}

@BindingAdapter("visible")
fun View.setVisible(visible: Boolean?) {
    visibility = if (visible == true) View.VISIBLE else View.GONE
}

@BindingAdapter("monumento")
fun MonumentoDetailInfoView.updateMonumentoDetails(monumento: Monumento?) {
    if (monumento != null) {
        setMonumento(monumento)
    }
}