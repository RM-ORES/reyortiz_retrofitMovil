package com.example.retrofitMovil.ui.pantallaDetalle

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitMovil.domain.modelo.Pedido

class PedidosAdapter(
    val context: Context,
    val actions: PedidoActions
) : ListAdapter<Pedido, PedidosAdapter.PedidoViewHolder>(DiffCallback()) {
    interface PedidoActions {
        fun onDelete(pedido:Pedido)
        fun onStartSelectMode(pedido: Pedido)
        fun itemClicked(pedido: Pedido)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidoViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: PedidoViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
    inner class PedidoViewHolder(view: View) : RecyclerView.ViewHolder(view){

    }
    class DiffCallback : DiffUtil.ItemCallback<Pedido>() {
        override fun areItemsTheSame(oldItem: Pedido, newItem: Pedido): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Pedido, newItem: Pedido): Boolean {
            return oldItem == newItem
        }
    }
}
