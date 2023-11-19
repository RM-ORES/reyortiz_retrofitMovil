package com.example.retrofitMovil.ui.pantallaMaster

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitMovil.domain.modelo.Mesa
import com.example.retrofitMovil.ui.SwipeGesture
import com.example.reyortiz_retrofitmovil.R
import com.example.reyortiz_retrofitmovil.databinding.ItemBinding

class MesasAdapter (
    val context:Context,
    val actions:MesaActions
) : ListAdapter<Mesa, MesasAdapter.MesaViewHolder>(DiffCallback()){
    private var selectMode: Boolean = false
    private var selectedMesas = mutableSetOf<Mesa>()

    interface MesaActions{
        fun onDelete(mesa: Mesa)
        fun onStartSelectMode(mesa: Mesa)
        fun addSelected(mesa:Mesa)
        fun removeSelected(mesa:Mesa)
        fun itemClicked(mesa: Mesa)
    }

    fun startSelectMode(){
        selectMode = true
        notifyDataSetChanged()
    }
    fun resetSelectMode(){
        selectMode = false
        selectedMesas.clear()
        notifyDataSetChanged()
    }
    fun setSelected(selectedMesasList: Set<Mesa>){
        selectedMesas.addAll(selectedMesasList)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MesaViewHolder {
        return MesaViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent, false)
        )
    }
    override fun onBindViewHolder(holder: MesaViewHolder, position: Int) {
        val mesa = getItem(position)
        mesa?.let{
            holder.bind(it)
        }
    }



    inner class MesaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val binding = ItemBinding.bind(itemView)
        fun bind(mesa : Mesa){
            with(binding){
                itemDescripcion.text = mesa.toString()
            }

            itemView.setOnClickListener{
                if (!selectMode){
                    actions.itemClicked(mesa)
                } else {
                    if(selectedMesas.contains(mesa)){
                        selectedMesas.remove(mesa)
                        actions.removeSelected(mesa)
                        notifyDataSetChanged()
                    } else {
                        selectedMesas.add(mesa)
                        actions.addSelected(mesa)
                        notifyDataSetChanged()
                    }
                }
            }
            itemView.setOnLongClickListener {
                if (!selectMode){
                    startSelectMode()
                    selectedMesas.add(mesa)
                    actions.onStartSelectMode(mesa)
                    notifyDataSetChanged()
                } else {
                    if(selectedMesas.contains(mesa)){
                        selectedMesas.remove(mesa)
                        actions.removeSelected(mesa)
                        notifyDataSetChanged()
                    } else {
                        selectedMesas.add(mesa)
                        actions.addSelected(mesa)
                        notifyDataSetChanged()
                    }
                }
                true
            }
            if(selectedMesas.contains(mesa)){
                itemView.setBackgroundColor(Color.MAGENTA)
            }
        }
    }
    class DiffCallback : DiffUtil.ItemCallback<Mesa>() {
        override fun areItemsTheSame(oldItem: Mesa, newItem: Mesa): Boolean {
            return oldItem.tableNumber == newItem.tableNumber
        }

        override fun areContentsTheSame(oldItem: Mesa, newItem: Mesa): Boolean {
            return oldItem == newItem
        }
    }

    val swipeGesture = object : SwipeGesture(context){
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            when(direction){
                ItemTouchHelper.LEFT -> {
                    if(!selectMode){
                        selectedMesas.remove(currentList[viewHolder.bindingAdapterPosition])
                        actions.onDelete(currentList[viewHolder.bindingAdapterPosition])
                    }
                }
            }
        }
    }
}