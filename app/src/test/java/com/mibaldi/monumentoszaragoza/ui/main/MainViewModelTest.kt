package com.mibaldi.monumentoszaragoza.ui.main

import arrow.core.Either
import com.mibaldi.monumentoszaragoza.testrules.CoroutinesTestRule
import com.mibaldi.monumentoszaragoza.testshared.sampleMonumento
import com.mibaldi.monumentoszaragoza.testshared.sampleResult
import com.mibaldi.monumentoszaragoza.usecases.GetMonumentosUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var getMonumentosUseCase: GetMonumentosUseCase
    private lateinit var vm: MainViewModel
    private val result = sampleResult.copy(result = listOf(sampleMonumento.copy(id = 1)))

    @Test
    fun `Monuments requested when UI screen starts`() = runTest {
        vm = buildViewModel()
        runCurrent()
        Mockito.verify(getMonumentosUseCase).getMonumentos("")
    }
    private fun buildViewModel(): MainViewModel {
        runBlocking {
            whenever((getMonumentosUseCase.getMonumentos(any()))).thenReturn(Either.Right(result))
        }
        return MainViewModel(getMonumentosUseCase)
    }
}