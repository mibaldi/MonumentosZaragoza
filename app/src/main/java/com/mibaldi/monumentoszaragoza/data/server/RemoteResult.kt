package com.mibaldi.monumentoszaragoza.data.server

data class RemoteResult(
    val totalCount: Int,
    val start: Int,
    val rows: Int,
    val result: List<RemoteMonumento>)

data class RemoteMonumento(
    val id: Int = 0,
    val description:String? ,
    val estilo:String?,
    val address: String?,
    val title: String?,
    val horario: String?,
    val image: String?,
    val geometry: RemoteGeometry?,
    val price: String?
    )

data class RemoteGeometry(
    val type: String?,
    val coordinates: List<Double>
)