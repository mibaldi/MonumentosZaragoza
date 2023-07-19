package com.mibaldi.monumentoszaragoza.usecases

import arrow.core.Either
import com.mibaldi.monumentoszaragoza.data.MonumentosRepository
import com.mibaldi.monumentoszaragoza.domain.Monumento
import com.mibaldi.monumentoszaragoza.domain.MyError
import com.mibaldi.monumentoszaragoza.domain.Result
import javax.inject.Inject

class GetMonumentoUseCase @Inject constructor(private val repository: MonumentosRepository){
    suspend fun getMonumento(id:Int): Either<MyError,Monumento> {
        return repository.getMonumento(id)
    }
}