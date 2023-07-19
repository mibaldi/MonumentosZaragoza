package com.mibaldi.monumentoszaragoza.domain

data class Result(
    val totalCount: Int,
    val start: Int,
    val rows: Int,
    val result: List<Monumento>)

data class Monumento(
    val id: Int,
    val description:String ,
    val estilo:String,
    val address: String,
    val title: String,
    val horario: String,
    val image: String,
    val geometry: Geometry?,
    val price: String
)

data class Geometry(
    val type: String,
    val coordinates: List<Double>
)
