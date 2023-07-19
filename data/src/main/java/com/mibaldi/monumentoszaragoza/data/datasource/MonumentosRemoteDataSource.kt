package com.mibaldi.monumentoszaragoza.data.datasource

import arrow.core.Either
import com.mibaldi.monumentoszaragoza.domain.Monumento
import com.mibaldi.monumentoszaragoza.domain.MyError
import com.mibaldi.monumentoszaragoza.domain.Result

interface MonumentosRemoteDataSource {

    suspend fun getMonumentos(point: String): Either<MyError,Result>
    suspend fun getMonumento(id: Int): Either<MyError, Monumento>
}