package com.example.retrofitMovil.ui.pantallaDetalle

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitMovil.domain.modelo.Pedido
import com.example.reyortiz_retrofitmovil.R
import com.example.reyortiz_retrofitmovil.databinding.ItemBinding

class PedidosAdapter(
    val context: Context,
    val actions: PedidoActions
) : ListAdapter<Pedido, PedidosAdapter.PedidoViewHolder>(DiffCallback()) {

    private var selectedPedidos = mutableSetOf<Pedido>()
    private var selectMode: Boolean = false

    interface PedidoActions {
        fun onDelete(pedido:Pedido)
        fun onStartSelectMode(pedido: Pedido)
        fun itemClicked(pedido: Pedido)
    }

    fun startSelectMode(){
        selectMode = true
        notifyDataSetChanged()
    }
    fun resetSelectMode(){
        selectMode = false
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidoViewHolder {
        return PedidoViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false))
    }

    override fun onBindViewHolder(holder: PedidoViewHolder, position: Int) = with(holder){
        bind(getItem(position))
    }


    inner class PedidoViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val binding = ItemBinding.bind(view)
        fun bind(item : Pedido){
//            itemView.setOnLongClickListener {
//                if (selectMode){
//
//                } else {
//
//                }
//            }
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
}
