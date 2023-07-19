package com.mibaldi.monumentoszaragoza.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.mibaldi.monumentoszaragoza.domain.Monumento
import com.mibaldi.monumentoszaragoza.domain.MyError
import com.mibaldi.monumentoszaragoza.usecases.GetMonumentosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMonumentosUseCase: GetMonumentosUseCase
) : ViewModel() {


    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        getMonumentos(null)
    }

    fun getMonumentos(location: LatLng?) {
        viewModelScope.launch {

            val locationString =  ""
            getMonumentosUseCase.getMonumentos(locationString)
                .fold(
                    ifLeft = {cause -> _state.update { it.copy(error=cause) }},
                    ifRight = { result ->
                        _state.update { UiState(monumentos = result.result) }
                    }
                )

        }
    }
    data class UiState(
        val loading: Boolean = false,
        val monumentos: List<Monumento>? = null,
        val error: MyError? = null
    )
}