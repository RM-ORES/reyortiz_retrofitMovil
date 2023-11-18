package com.example.retrofitMovil.ui.pantallaMaster

import android.content.Intent
import android.os.Bundle
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.retrofitMovil.domain.modelo.Mesa
import com.example.retrofitMovil.domain.modelo.Pedido
import com.example.retrofitMovil.ui.pantallaDetalle.DetalleActivity
import com.example.retrofitMovil.utilities.Constantes
import com.example.reyortiz_retrofitmovil.R
import com.example.reyortiz_retrofitmovil.databinding.ActivityMasterBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MasterActivity : AppCompatActivity() {
    private val viewModel: MasterViewModel by viewModels()
    private lateinit var binding: ActivityMasterBinding
    private lateinit var mesasAdapter: MesasAdapter
    private var anteriorState: MasterState? = null
    private var actionMode: ActionMode? = null

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
                    configContextBar()
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
//        configAppBar()
    }

    private fun observarViewModel() {
        viewModel.uiState.observe(this) { state ->
            if (state.selectMode){
                actionMode?.title = "${state.selectedMesas.size}" + Constantes.SELECTED
            }
            if (state.selectMode != anteriorState?.selectMode) {
                if (state.selectMode) {
                    mesasAdapter.startSelectMode()
                } else {
                    mesasAdapter.resetSelectMode()
                }
            }

            anteriorState = state
        }
    }

//    private fun configAppBar() {
//        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
//            when (menuItem.itemId) {
//                R.id.delete -> {
//                    viewModel.handleEvent(MasterEvent.DeleteSeleccionadas)
//                    true
//                }
//
//                else -> {
//                    false
//                }
//            }
//        }
//    }

    private fun configContextBar() =
        object : ActionMode.Callback {
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