package com.mibaldi.monumentoszaragoza.ui.detail

import app.cash.turbine.test
import com.mibaldi.monumentoszaragoza.apptestshared.buildRemoteResult
import com.mibaldi.monumentoszaragoza.apptestshared.buildRepositoryWith
import com.mibaldi.monumentoszaragoza.data.server.RemoteResult
import com.mibaldi.monumentoszaragoza.testrules.CoroutinesTestRule
import com.mibaldi.monumentoszaragoza.usecases.GetMonumentoUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailIntegrationTests {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun `UI is updated with the monument on start`() = runTest {
        val vm = buildViewModelWith(
            buildRemoteResult(2,3)
        )
        vm.getMonumento(2)
        vm.state.test {
            Assert.assertEquals(DetailViewModel.UiState(loading = false), awaitItem())
            Assert.assertEquals(DetailViewModel.UiState(loading = true), awaitItem())
            Assert.assertEquals(2, awaitItem().monumento!!.id)
            cancel()
        }
    }


    private fun buildViewModelWith(
        remoteData: RemoteResult = buildRemoteResult(2,3)
    ): DetailViewModel {
        val monumentoRepository = buildRepositoryWith(remoteData)
        val getCharacterUseCase = GetMonumentoUseCase(monumentoRepository)
        return DetailViewModel(getCharacterUseCase)
    }
}