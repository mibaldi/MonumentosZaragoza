package com.mibaldi.monumentoszaragoza.ui.main

import app.cash.turbine.test
import com.mibaldi.monumentoszaragoza.apptestshared.buildRemoteResult
import com.mibaldi.monumentoszaragoza.apptestshared.buildRepositoryWith
import com.mibaldi.monumentoszaragoza.data.server.RemoteResult
import com.mibaldi.monumentoszaragoza.testrules.CoroutinesTestRule
import com.mibaldi.monumentoszaragoza.usecases.GetMonumentoUseCase
import com.mibaldi.monumentoszaragoza.usecases.GetMonumentosUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainIntegrationTests {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun `data is loaded from server `() = runTest {
        val remoteData = buildRemoteResult(4, 5, 6)
        val vm = buildViewModelWith(remoteData
        )

        vm.state.test {
            assertEquals(MainViewModel.UiState(monumentos = null, loading = false), awaitItem())
            assertEquals(MainViewModel.UiState(monumentos = null, loading = true), awaitItem())

            val monumentos = awaitItem().monumentos!!
            Assert.assertEquals("Title 4", monumentos[0].title)
            Assert.assertEquals("Title 5", monumentos[1].title)
            Assert.assertEquals("Title 6", monumentos[2].title)
            cancel()
        }
    }

    private fun buildViewModelWith(
        remoteResult: RemoteResult
    ): MainViewModel {

        val monumentosRepository = buildRepositoryWith(remoteResult)
        val getMonumentosUseCase = GetMonumentosUseCase(monumentosRepository)
        return MainViewModel(getMonumentosUseCase)
    }
}