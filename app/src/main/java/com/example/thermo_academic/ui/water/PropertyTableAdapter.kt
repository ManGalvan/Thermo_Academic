package com.example.thermo_academic.ui.water

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.thermo_academic.R
import com.example.thermo_academic.model.UiPropertyRow

class PropertyTableAdapter(
    private var items: List<UiPropertyRow>
) : RecyclerView.Adapter<PropertyTableAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val label: TextView = view.findViewById(R.id.txtLabel)
        val value: TextView = view.findViewById(R.id.txtValue)
        val unit: TextView = view.findViewById(R.id.txtUnit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_property_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.label.text = item.name
        holder.value.text = item.value
        holder.unit.text = item.unit
    }

    override fun getItemCount(): Int = items.size

    fun update(newItems: List<UiPropertyRow>) {
        items = newItems
        notifyDataSetChanged()
    }
}
