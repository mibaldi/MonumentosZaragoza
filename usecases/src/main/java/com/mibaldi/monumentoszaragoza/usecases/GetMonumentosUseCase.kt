package com.mibaldi.monumentoszaragoza.usecases

import arrow.core.Either
import com.mibaldi.monumentoszaragoza.data.MonumentosRepository
import com.mibaldi.monumentoszaragoza.domain.MyError
import com.mibaldi.monumentoszaragoza.domain.Result
import javax.inject.Inject

class GetMonumentosUseCase @Inject constructor(private val repository: MonumentosRepository){
    suspend fun getMonumentos(point:String): Either<MyError,Result> {
        return repository.getMonumentos(point)
    }
}