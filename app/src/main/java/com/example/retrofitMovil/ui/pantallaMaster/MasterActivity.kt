package com.example.retrofitMovil.ui.pantallaMaster

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.retrofitMovil.domain.modelo.Pedido
import com.example.reyortiz_retrofitmovil.R
import com.example.reyortiz_retrofitmovil.databinding.ActivityMasterBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MasterActivity : AppCompatActivity() {
    private val viewModel: MasterViewModel by viewModels()
    private lateinit var binding: ActivityMasterBinding
    private lateinit var mesasAdapter: MesasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMasterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mesasAdapter = MesasAdapter(this,
            object : MesasAdapter.MesaActions{
                override fun onDelete(pedido: Pedido) {
                    TODO("Not yet implemented")
                }

                override fun itemClicked(pedido: Pedido) {
                    TODO("Not yet implemented")
                }

                override fun onStartSelectMode(pedido: Pedido) {
                    TODO("Not yet implemented")
                }
            })
        binding.rvMesas.adapter = mesasAdapter

        val touchHelper = ItemTouchHelper(mesasAdapter.swipeGesture)
        touchHelper.attachToRecyclerView(binding.rvMesas)
    }
    private fun observarViewModel(){
        viewModel.uiState.observe(this){state ->

        }
    }
    private fun configAppBar(){
        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId){
               R.id.delete -> {
                   //handle event delete
                   true}
                else -> {false}
            }
        }
    }

}