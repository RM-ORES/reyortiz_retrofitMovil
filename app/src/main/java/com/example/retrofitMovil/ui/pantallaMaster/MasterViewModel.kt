package com.example.retrofitMovil.ui.pantallaMaster

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitMovil.domain.modelo.Mesa
import com.example.retrofitMovil.domain.usecases.mesa.DeleteMesaUsecase
import com.example.retrofitMovil.domain.usecases.mesa.GetAllMesaUsecase
import com.example.retrofitMovil.utilities.Constantes
import com.example.retrofitMovil.utilities.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class MasterViewModel @Inject constructor(
    private val getAllMesaUsecase: GetAllMesaUsecase,
    private val deleteMesaUsecase: DeleteMesaUsecase
) : ViewModel() {
    private val _uiState = MutableLiveData<MasterState>()
    val uiState: LiveData<MasterState> get() = _uiState
    private val selectedMesas = mutableSetOf<Mesa>()
    private val _sharedFlow = MutableSharedFlow<String>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    init {
        getMesas()
    }

    fun handleEvent(event: MasterEvent) {
        when (event) {
            MasterEvent.GetMesas -> getMesas()
            MasterEvent.ErrorVisto -> _uiState.value = _uiState.value?.copy(error = null)

            MasterEvent.StartSelectMode -> _uiState.value = _uiState.value?.copy(selectMode = true)
            MasterEvent.ResetSelectMode -> resetSelectMode()

            is MasterEvent.DeleteMesa -> deleteMesa(event.id)
            MasterEvent.DeleteSeleccionadas -> deleteSelected()
            is MasterEvent.SeleccionarMesa -> selectMesa(event.mesa)
            is MasterEvent.RemoveSeleccionada -> removeSelected(event.mesa)
        }
    }

    private fun getMesas() {
        viewModelScope.launch {
            when (val list = getAllMesaUsecase()) {
                is NetworkResult.Success -> {
                    _uiState.value = MasterState(mesas = list.data as List<Mesa>)
                    Timber.i((list.data as List<Mesa>).toString())
                }

                is NetworkResult.Error -> {
                    _uiState.value =
                        MasterState(mesas = emptyList(), error = list.message.toString())
                }
            }
        }
    }

    private fun deleteMesa(id: Int) {
        viewModelScope.launch {
            when (deleteMesaUsecase(id)) {
                is NetworkResult.Error -> _uiState.value =
                    _uiState.value?.copy(error = Constantes.ERROR)

                is NetworkResult.Success -> _uiState.value =
                    _uiState.value?.copy(error = Constantes.BORRADO_P)
            }
        }
        getMesas()
    }

    private fun deleteSelected() {
        for (mesa: Mesa in selectedMesas) {
            deleteMesa(mesa.tableNumber)
        }
        resetSelectMode()
        getMesas()
    }

    private fun selectMesa(mesa: Mesa) {
        selectedMesas.add(mesa)
        _uiState.value = _uiState.value?.copy(selectedMesas = selectedMesas)
    }

    private fun resetSelectMode() {
        selectedMesas.clear()
        _uiState.value = _uiState.value?.copy(selectMode = false, selectedMesas = selectedMesas)
    }

    private fun removeSelected(mesa: Mesa) {
        selectedMesas.remove(mesa)
        _uiState.value = _uiState.value?.copy(selectedMesas = selectedMesas)
        if (selectedMesas.isEmpty()) {
            resetSelectMode()
        }
    }
}