package com.example.retrofitMovil.ui.pantallaMaster

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.retrofitMovil.domain.modelo.Mesa
import com.example.retrofitMovil.ui.pantallaDetalle.DetalleActivity
import com.example.retrofitMovil.utilities.Constantes
import com.example.reyortiz_retrofitmovil.R
import com.example.reyortiz_retrofitmovil.databinding.ActivityMasterBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MasterActivity : AppCompatActivity() {
    private val viewModel: MasterViewModel by viewModels()
    private lateinit var binding: ActivityMasterBinding
    private lateinit var mesasAdapter: MesasAdapter
    private var anteriorState: MasterState? = null
    private var actionMode: ActionMode? = null
    private val callback by lazy {
        configContextBar()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMasterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mesasAdapter = MesasAdapter(this,
            object : MesasAdapter.MesaActions {
                override fun onDelete(mesa: Mesa) {
                    viewModel.handleEvent(MasterEvent.DeleteMesa(mesa.tableNumber))
                }

                override fun itemClicked(mesa: Mesa) {
                    click(mesa)
                }

                override fun onStartSelectMode(mesa: Mesa) {
                    startSupportActionMode(callback)?.let {
                        actionMode = it
                    }
                    viewModel.handleEvent(MasterEvent.StartSelectMode)
                    viewModel.handleEvent(MasterEvent.SeleccionarMesa(mesa))
                }

                override fun addSelected(mesa: Mesa) {
                    viewModel.handleEvent(MasterEvent.SeleccionarMesa(mesa))
                }

                override fun removeSelected(mesa: Mesa) {
                    viewModel.handleEvent(MasterEvent.RemoveSeleccionada(mesa))
                }
            })
        binding.rvMesas.adapter = mesasAdapter

        val touchHelper = ItemTouchHelper(mesasAdapter.swipeGesture)
        touchHelper.attachToRecyclerView(binding.rvMesas)

        observarViewModel()
    }

    private fun observarViewModel() {
        viewModel.uiState.observe(this) { state ->
            state.error?.let { error ->
                Toast.makeText(this@MasterActivity, error, Toast.LENGTH_SHORT).show()
                viewModel.handleEvent(MasterEvent.ErrorVisto)
            }
            if(state.mesas != anteriorState?.mesas){
                mesasAdapter.submitList(state.mesas)
            }
            if(state.selectMode){
                actionMode?.title = "${state.selectedMesas.size}" + Constantes.SELECTED
            }
            if (state.selectMode != anteriorState?.selectMode) {
                if (state.selectMode) {
                    mesasAdapter.setSelected(state.selectedMesas)
                } else {
                    mesasAdapter.resetSelectMode()
                    actionMode?.finish()
                }
            }
            anteriorState = state


            val context = this
            lifecycleScope.launch {
                viewModel.sharedFlow.collect{ error->
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun configContextBar()= object : ActionMode.Callback {
            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                menuInflater.inflate(R.menu.menu_context_bar, menu)
                return true
            }

            override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                return false
            }

            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                return when (item?.itemId) {
                    R.id.delete -> {
                        viewModel.handleEvent(MasterEvent.DeleteSeleccionadas)
                        true
                    }

                    else -> false
                }
            }

            override fun onDestroyActionMode(mode: ActionMode?) {
                viewModel.handleEvent(MasterEvent.ResetSelectMode)
            }
        }

    private fun click(mesa: Mesa) {
        val intent = Intent(this, DetalleActivity::class.java)
        intent.putExtra(Constantes.TABLE_NUMBER, mesa.tableNumber)
        startActivity(intent)
    }

}