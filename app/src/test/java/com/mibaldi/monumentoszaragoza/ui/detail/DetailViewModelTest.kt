package com.mibaldi.monumentoszaragoza.ui.detail

import app.cash.turbine.test
import arrow.core.Either
import com.mibaldi.monumentoszaragoza.testrules.CoroutinesTestRule
import com.mibaldi.monumentoszaragoza.testshared.sampleMonumento
import com.mibaldi.monumentoszaragoza.usecases.GetMonumentoUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var getMonumentoUseCase: GetMonumentoUseCase


    private lateinit var vm: DetailViewModel

    private val monumento = sampleMonumento.copy(id = 2)

    @Test
    fun `UI is updated with the monument on start`() = runTest {
        vm = buildViewModel()
        vm.getMonumento(1)
        vm.state.test {
            assertEquals(DetailViewModel.UiState(loading = false), awaitItem())
            assertEquals(DetailViewModel.UiState(loading = true), awaitItem())
            assertEquals(DetailViewModel.UiState(monumento = monumento), awaitItem())

            cancel()
        }
    }

    private fun buildViewModel(): DetailViewModel {
        runBlocking {
            whenever(getMonumentoUseCase.getMonumento(any())).thenReturn(Either.Right(monumento))
        }
        return DetailViewModel(getMonumentoUseCase)
    }
}