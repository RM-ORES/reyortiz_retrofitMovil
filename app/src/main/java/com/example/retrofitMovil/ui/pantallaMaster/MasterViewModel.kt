package com.example.retrofitMovil.ui.pantallaMaster

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitMovil.domain.modelo.Mesa
import com.example.retrofitMovil.domain.usecases.mesa.GetAllMesaUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MasterViewModel @Inject constructor(private val getAllMesaUsecase: GetAllMesaUsecase) : ViewModel(){
    private val _uiState = MutableLiveData<MasterState>()
    val uiState: LiveData<MasterState> get() = _uiState
    val selectedMesas = mutableListOf<Mesa>()
    init{
        _uiState.value = MasterState(mesas = emptyList(), error = null)
    }
    fun handleEvent(event: MasterEvent){
        when(event){
            MasterEvent.GetMesas-> getMesas()
            MasterEvent.ErrorVisto -> _uiState.value = _uiState.value?.copy(error = null)

            MasterEvent.StartSelectMode -> _uiState.value = _uiState.value?.copy(selectMode = true)
            MasterEvent.ResetSelectMode -> resetSelectMode()

            is MasterEvent.DeleteMesa -> deleteMesa(event.id)
            MasterEvent.DeleteSeleccionadas -> deleteSelected()
            is MasterEvent.SeleccionarMesa -> selectMesa(event.mesa)
            is MasterEvent.RemoveSeleccionada -> removeSelected(event.mesa)
        }
    }
    private fun getMesas(){
        viewModelScope.launch {
            var list = getMesas()
            when (list){
                is
            }
        }
    }
    private fun resetSelectMode(){
        selectedMesas.clear()
        _uiState.value = _uiState.value?.copy(selectMode = false, selectedMesas = selectedMesas)
    }

    private fun deleteMesa(id : Int){

    }
    private fun deleteSelected(){

    }
    private fun selectMesa(mesa: Mesa){
        if (isSelected(mesa)){
            selectedMesas.remove(mesa)
        } else {
            selectedMesas.add(mesa)
        }
        _uiState.value = _uiState.value?.copy(selectedMesas = selectedMesas)
    }
    private fun isSelected(mesa:Mesa): Boolean{
        return selectedMesas.contains(mesa)
    }
    private fun removeSelected(mesa: Mesa){
        selectedMesas.remove(mesa)
        _uiState.value = _uiState.value?.copy(selectedMesas = selectedMesas)
    }
}