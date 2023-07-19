package com.mibaldi.monumentoszaragoza.testshared

import com.mibaldi.monumentoszaragoza.domain.Geometry
import com.mibaldi.monumentoszaragoza.domain.Monumento
import com.mibaldi.monumentoszaragoza.domain.Result

val sampleMonumento = Monumento(
    2,
    "description",
    "estilo",
    "Address",
    "Title",
    "horario",
    "image",
    Geometry("", listOf(0.3444,40.112123)),
    "price"
)
val sampleResult =
    Result(
        180,
        0,
        50,
        listOf(sampleMonumento)
    )