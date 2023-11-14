package com.example.retrofitMovil.ui.pantallaMaster

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitMovil.domain.modelo.Mesa
import com.example.retrofitMovil.domain.modelo.Pedido

class MesasAdapter (
    val context:Context,
    val actions:MesaActions
) : ListAdapter<Mesa, MesasAdapter.MesaViewHolder>(DiffCallback()){
    interface MesaActions{
        fun onDelete(pedido:Pedido)
        fun onStartSelectMode(pedido: Pedido)
        fun itemClicked(pedido: Pedido)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MesaViewHolder {
        TODO("Not yet implemented")
    }
    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
    override fun onBindViewHolder(holder: MesaViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
    inner class MesaViewHolder(view: View) : RecyclerView.ViewHolder(view){

    }
    class DiffCallback : DiffUtil.ItemCallback<Mesa>() {
        override fun areItemsTheSame(oldItem: Mesa, newItem: Mesa): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Mesa, newItem: Mesa): Boolean {
            return oldItem == newItem
        }
    }


}