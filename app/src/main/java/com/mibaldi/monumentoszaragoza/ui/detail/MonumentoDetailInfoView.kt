package com.mibaldi.monumentoszaragoza.ui.detail

import android.content.Context
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

            bold { append(context.getString(R.string.horario)) }
            appendLine(horario)

            bold { append(context.getString(R.string.price)) }
            appendLine(price)
            bold { append(context.getString(R.string.estilo)) }
            appendLine(estilo)
            bold { append(context.getString(R.string.address)) }
            appendLine(address)

        }
    }
}