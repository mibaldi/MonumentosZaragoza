package com.mibaldi.monumentoszaragoza.ui.detail

import android.content.Context
import android.text.Html
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.mibaldi.monumentoszaragoza.R
import com.mibaldi.monumentoszaragoza.domain.Monumento


class MonumentoDetailInfoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    fun setMonumento(monumento: Monumento) = monumento.apply {

        text = buildSpannedString {
            if (title.isNotEmpty()){
                bold { append(context.getString(R.string.nombre)) }
                appendLine(Html.fromHtml(title, Html.FROM_HTML_MODE_COMPACT))
            }
            if (horario.isNotEmpty()){
                bold { append(context.getString(R.string.horario)) }
                appendLine(Html.fromHtml(horario, Html.FROM_HTML_MODE_COMPACT))
            }
            if (price.isNotEmpty()){
                bold { append(context.getString(R.string.price)) }
                appendLine(Html.fromHtml(price, Html.FROM_HTML_MODE_COMPACT))
            }
            if (estilo.isNotEmpty()){
                bold { append(context.getString(R.string.estilo)) }
                appendLine(Html.fromHtml(estilo, Html.FROM_HTML_MODE_COMPACT))
            }
            if (address.isNotEmpty()){
                bold { append(context.getString(R.string.address)) }
                appendLine(Html.fromHtml(address, Html.FROM_HTML_MODE_COMPACT))
            }
        }
    }
}