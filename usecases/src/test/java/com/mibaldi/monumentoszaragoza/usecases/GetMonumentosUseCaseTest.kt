package com.mibaldi.monumentoszaragoza.usecases

import com.mibaldi.monumentoszaragoza.data.MonumentosRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito

class GetMonumentosUseCaseTest {
    @Test
    fun getMonumentos() = runBlocking {

        val monumentosRepository = Mockito.mock<MonumentosRepository>()
        val requestMonumentosUseCase = GetMonumentosUseCase(monumentosRepository)

        requestMonumentosUseCase.getMonumentos("")

        Mockito.verify(monumentosRepository).getMonumentos("")
    }
}
