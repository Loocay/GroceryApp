package com.example.groceryapp.Grocery


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.Database.GroceryEntities
import com.example.groceryapp.R

class GroceryRVAdapter (
    var list: List<GroceryEntities>,
    val groceryItemClickInterface: GroceryItemClickInterface
    ) : RecyclerView.Adapter<GroceryRVAdapter.GroceryViewHolder>() {


    inner class GroceryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val itemName = itemView.findViewById<TextView>(R.id.ItemName)
        val itemQuantity = itemView.findViewById<TextView>(R.id.ItemQuantity)
        val itemRate = itemView.findViewById<TextView>(R.id.ItemRate)
        val itemDelete = itemView.findViewById<ImageView>(R.id.ItemDelete)
        val itemAmount = itemView.findViewById<TextView>(R.id.TotalAmount)
        val itemCost = itemView.findViewById<TextView>(R.id.TotalCost)

    }

    interface GroceryItemClickInterface{
        fun onItemClick(groceryEntities: GroceryEntities)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grocery_rv_items,parent, false)
        return GroceryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
        holder.itemName.text = list.get(position).itemName
        holder.itemQuantity.text = list.get(position).itemQuantity.toString()
        holder.itemRate.text = "₸ " + list.get(position).itemPrice.toString()
        val itemTotal : Int = list.get(position).itemPrice * list.get(position).itemQuantity
        holder.itemAmount.text = "₸ " + itemTotal.toString()
        holder.itemDelete.setOnClickListener {
            groceryItemClickInterface.onItemClick(list.get(position))
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}