package com.mibaldi.monumentoszaragoza.data.server

import arrow.core.Either
import com.mibaldi.monumentoszaragoza.data.datasource.MonumentosRemoteDataSource
import com.mibaldi.monumentoszaragoza.data.tryCall
import com.mibaldi.monumentoszaragoza.domain.Geometry
import com.mibaldi.monumentoszaragoza.domain.Monumento
import com.mibaldi.monumentoszaragoza.domain.MyError
import com.mibaldi.monumentoszaragoza.domain.Result
import javax.inject.Inject

class MonumentosServerDataSource @Inject constructor(private val remoteService: RemoteService):
    MonumentosRemoteDataSource {
    override suspend fun getMonumentos(point:String): Either<MyError, Result> = tryCall {
        remoteService
            .getMonumentos(point)
            .toDomainModel()
    }
    override suspend fun getMonumento(id: Int): Either<MyError, Monumento> = tryCall {
        remoteService
            .getMonumento(id)
            .toDomainModel()
    }

    private fun RemoteResult.toDomainModel() : Result {
        return Result(
            totalCount,
            start,
            rows,
            result.map { it.toDomainModel() }
        )
    }



    private fun RemoteMonumento.toDomainModel() : Monumento {
        return Monumento(
            id,
            description ?:"",
            estilo?:"",
            address?:"",
            title?:"",
            horario?:"",
            image?:"",
            geometry?.toDomainModel(),
            price?:""
        )
    }



    private fun RemoteGeometry.toDomainModel() : Geometry {
        return Geometry(type?:"",coordinates)
    }
}