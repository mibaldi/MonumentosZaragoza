package com.mibaldi.monumentoszaragoza.apptestshared

import com.mibaldi.monumentoszaragoza.data.server.RemoteMonumento
import com.mibaldi.monumentoszaragoza.data.server.RemoteResult
import com.mibaldi.monumentoszaragoza.data.server.RemoteService

class FakeRemoteService(private val remoteResult: RemoteResult) :
    RemoteService {


    override suspend fun getMonumentos(point: String, distance: String): RemoteResult {
        return remoteResult
    }

    override suspend fun getMonumento(id: Int): RemoteMonumento {
        return remoteResult.result.first()
    }

}