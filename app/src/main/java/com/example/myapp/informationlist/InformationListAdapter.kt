package com.example.myapp.informationlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.R
import com.example.myapp.model.Product

class InformationListAdapter(
    private val items: List<Product>,
    private val onItemClickListener: (item: Product) -> Unit
) : RecyclerView.Adapter<InformationListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.information_list_item,
                parent,
                false
            )
        )

        viewHolder.itemView.setOnClickListener {
            onItemClickListener.invoke(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.tvDescription.text = StringBuilder().apply {
            append("Nombre: ")
            append(items[position].title)
        }

        viewHolder.tvValue.text = items[position].description
        viewHolder.tvPrice.text =
            StringBuilder().apply {
                append("Precio: ")
                append(items[position].price.toString())
            }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvDescription: TextView
        val tvValue: TextView
        val tvPrice: TextView

        init {

            tvDescription = view.findViewById(R.id.tv_title)
            tvValue = view.findViewById(R.id.tv_description)
            tvPrice = view.findViewById(R.id.tv_price)
        }

    }
}

interface ShareButtonListener {
    fun onShareButtonClicked() {
    }
}
