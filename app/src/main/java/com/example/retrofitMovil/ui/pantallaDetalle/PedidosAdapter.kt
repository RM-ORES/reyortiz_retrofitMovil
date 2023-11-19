package com.example.retrofitMovil.ui.pantallaDetalle

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitMovil.domain.modelo.Pedido
import com.example.retrofitMovil.ui.SwipeGesture
import com.example.reyortiz_retrofitmovil.R
import com.example.reyortiz_retrofitmovil.databinding.ItemBinding

class PedidosAdapter(
    val context: Context,
    val actions: PedidoActions
) : ListAdapter<Pedido, PedidosAdapter.PedidoViewHolder>(DiffCallback()) {


    interface PedidoActions {
        fun onDelete(pedido: Pedido)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidoViewHolder {
        return PedidoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PedidoViewHolder, position: Int) = with(holder) {
        bind(getItem(position))
    }


    inner class PedidoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemBinding.bind(view)
        fun bind(pedido: Pedido) {
            with(binding){
                itemDescripcion.text = pedido.toString()
            }
        }
    }


    class DiffCallback : DiffUtil.ItemCallback<Pedido>() {
        override fun areItemsTheSame(oldItem: Pedido, newItem: Pedido): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Pedido, newItem: Pedido): Boolean {
            return oldItem == newItem
        }
    }

    val swipeGesture = object : SwipeGesture(context) {
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            when (direction) {
                ItemTouchHelper.LEFT -> {
                    actions.onDelete(currentList[viewHolder.bindingAdapterPosition])
                }
            }
        }
    }
}
