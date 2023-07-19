package com.mibaldi.monumentoszaragoza.usecases

import com.mibaldi.monumentoszaragoza.data.MonumentosRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito

class GetMonumentoUseCaseTest {
    @Test
    fun getMonumento()  = runBlocking {

        val monumentosRepository = Mockito.mock<MonumentosRepository>()
        val requestMonumentoUseCase = GetMonumentoUseCase(monumentosRepository)

        requestMonumentoUseCase.getMonumento(1)

        Mockito.verify(monumentosRepository).getMonumento(1)
    }
}