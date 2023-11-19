package com.example.retrofitMovil.ui.pantallaDetalle

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.retrofitMovil.domain.modelo.Pedido
import com.example.retrofitMovil.ui.pantallaMaster.MasterActivity
import com.example.retrofitMovil.utilities.Constantes
import com.example.reyortiz_retrofitmovil.R
import com.example.reyortiz_retrofitmovil.databinding.ActivityDetalleBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.time.LocalDateTime

@AndroidEntryPoint
class DetalleActivity : AppCompatActivity() {
    private val viewModel: DetalleViewModel by viewModels()
    private lateinit var binding: ActivityDetalleBinding
    private lateinit var pedidosAdapter: PedidosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intent.extras?.let {
            viewModel.handleEvent(DetalleEvent.SetMesa(it.getInt(Constantes.TABLE_NUMBER)))
        }
        pedidosAdapter = PedidosAdapter(this,
            object : PedidosAdapter.PedidoActions {
                override fun onDelete(pedido: Pedido) {
                    pedido.id?.let { DetalleEvent.DeletePedido(it) }
                }
            })
        binding.rvPedidos.adapter = pedidosAdapter

        val touchHelper = ItemTouchHelper(pedidosAdapter.swipeGesture)
        touchHelper.attachToRecyclerView(binding.rvPedidos)

        observarViewModel()
        eventos()
    }

    private fun observarViewModel() {
        viewModel.uiState.observe(this@DetalleActivity) { state ->
            state.error?.let { error ->
                Toast.makeText(this@DetalleActivity, error, Toast.LENGTH_SHORT).show()
                viewModel.handleEvent(DetalleEvent.ErrorVisto)
            }
            with(binding) {
                if (state.error == null) {
                    val mesa = viewModel.uiState.value?.mesa
                    mesa?.let {
                        Timber.i(mesa.toString())
                        numMesa.text = it.tableNumber.toString()
                        numAsientos.text = it.seats.toString()
                    }
                    val pedidos = viewModel.uiState.value?.pedidos
                    pedidos?.let {
                        Timber.i(pedidos.toString())
                        pedidosAdapter.submitList(pedidos)
                    } ?:{
                        textPedidos.text = Constantes.NO_PEDIDOS
                    }
                }
                if (state.fin){
                    val intent  = Intent(this@DetalleActivity, MasterActivity::class.java)
                    startActivity(intent);
                }
            }
        }
    }

    private fun eventos() {
        with(binding) {
            addButton.setOnClickListener {
                viewModel.uiState.value?.mesa?.let {
                    viewModel.handleEvent(
                        DetalleEvent
                            .AddPedido(
                                Pedido(
                                    null,
                                    it.tableNumber,
                                    Integer.parseInt(editCustomer.text.toString()),
                                    LocalDateTime.now()
                                )
                            )
                    )
                }
            }
            topAppBar.setOnMenuItemClickListener { menuItem ->
                when(menuItem.itemId){
                    R.id.delete ->{
                        viewModel.handleEvent(DetalleEvent.DeleteMesa)
                        true
                    }
                    else -> {false}
                }
            }

        }
    }
}