package com.mibaldi.monumentoszaragoza.data

import arrow.core.Either
import com.mibaldi.monumentoszaragoza.data.datasource.MonumentosRemoteDataSource
import com.mibaldi.monumentoszaragoza.testshared.sampleResult
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class MonumentosRepositoryTest {
    @Mock
    lateinit var remoteDataSource: MonumentosRemoteDataSource

    private lateinit var monumentosRepository: MonumentosRepository

    @Before
    fun setUp() {
        monumentosRepository = MonumentosRepository(remoteDataSource)
    }
    @Test
    fun getMonuments() = runBlocking{
        whenever(remoteDataSource.getMonumentos(any())).thenReturn(Either.Right(sampleResult))
        val result = monumentosRepository.getMonumentos("")
        assertEquals(Either.Right(sampleResult), result)
    }

    @Test
    fun getMonument() = runBlocking{
        whenever(remoteDataSource.getMonumento(any())).thenReturn(Either.Right(sampleResult.result.first()))
        val result = monumentosRepository.getMonumento(1)
        assertEquals(Either.Right(sampleResult.result.first()), result)
    }
}