package com.example.retrofitMovil.ui.pantallaDetalle

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofitMovil.domain.modelo.Pedido
import com.example.reyortiz_retrofitmovil.databinding.ActivityDetalleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetalleActivity : AppCompatActivity() {
    private val viewModel: DetalleViewModel by viewModels()
    private lateinit var binding: ActivityDetalleBinding
    private lateinit var pedidosAdapter: PedidosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pedidosAdapter = PedidosAdapter(this,
            object : PedidosAdapter.PedidoActions{
                override fun onDelete(pedido: Pedido) {
                    TODO("Not yet implemented")
                }

                override fun onStartSelectMode(pedido: Pedido) {
                    TODO("Not yet implemented")
                }

                override fun itemClicked(pedido: Pedido) {
                    TODO("Not yet implemented")
                }

            })

    }
}