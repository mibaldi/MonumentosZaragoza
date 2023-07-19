package com.mibaldi.monumentoszaragoza.data

import arrow.core.Either
import com.mibaldi.monumentoszaragoza.data.datasource.MonumentosRemoteDataSource
import com.mibaldi.monumentoszaragoza.domain.Monumento
import com.mibaldi.monumentoszaragoza.domain.MyError
import com.mibaldi.monumentoszaragoza.domain.Result
import javax.inject.Inject

class MonumentosRepository @Inject constructor(private val remoteDataSource: MonumentosRemoteDataSource) {

    suspend fun getMonumentos(point:String):Either<MyError,Result>{
        return remoteDataSource.getMonumentos(point)
    }

    suspend fun getMonumento(id: Int): Either<MyError, Monumento> {
        return remoteDataSource.getMonumento(id)
    }

}