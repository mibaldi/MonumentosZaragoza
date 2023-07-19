package com.mibaldi.monumentoszaragoza.apptestshared

import com.mibaldi.monumentoszaragoza.data.MonumentosRepository
import com.mibaldi.monumentoszaragoza.data.server.MonumentosServerDataSource
import com.mibaldi.monumentoszaragoza.data.server.RemoteGeometry
import com.mibaldi.monumentoszaragoza.data.server.RemoteMonumento
import com.mibaldi.monumentoszaragoza.data.server.RemoteResult

fun buildRepositoryWith(remoteResult: RemoteResult
): MonumentosRepository {
    val remoteDataSource = MonumentosServerDataSource(FakeRemoteService(remoteResult))
    return MonumentosRepository( remoteDataSource)
}

fun buildRemoteResult(vararg id: Int) = RemoteResult(180,0,50, buildRemoteMonumento(id))

fun buildRemoteMonumento(id: IntArray) = id.map {
    RemoteMonumento(
        it,
        "description $it",
        "estilo",
        "Address",
        "Title $it",
        "horario",
        "image",
        RemoteGeometry("", listOf(0.3444,40.112123)),
        "price"
    )
}