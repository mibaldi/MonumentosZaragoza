package com.mibaldi.monumentoszaragoza.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mibaldi.monumentoszaragoza.domain.Monumento
import com.mibaldi.monumentoszaragoza.domain.MyError
import com.mibaldi.monumentoszaragoza.usecases.GetMonumentoUseCase
import com.mibaldi.monumentoszaragoza.usecases.GetMonumentosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getMonumentoUseCase: GetMonumentoUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    fun getMonumento(id: Int){
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true)
            getMonumentoUseCase.getMonumento(id)
                .fold(
                    ifLeft = {cause -> _state.update { it.copy(error = cause, loading = false) }},
                    ifRight = {result ->
                        _state.update { UiState(monumento = result) }
                    }
                )
            _state.value = _state.value.copy(loading = false)
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val monumento: Monumento? = null,
        val error: MyError? = null
    )
}