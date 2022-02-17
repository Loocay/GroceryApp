package com.example.groceryapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.Database.GroceryDatabase
import com.example.groceryapp.Database.GroceryEntities
import com.example.groceryapp.Database.GroceryRepository
import com.example.groceryapp.Grocery.GroceryRVAdapter
import com.example.groceryapp.Grocery.GroceryViewModel
import com.example.groceryapp.Grocery.GroceryViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), GroceryRVAdapter.GroceryItemClickInterface{
    lateinit var itemsRV: RecyclerView
    lateinit var addFAB: FloatingActionButton
    lateinit var list: List<GroceryEntities>
    lateinit var groceryRVAdapter: GroceryRVAdapter
    lateinit var groceryViewModel: GroceryViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemsRV = findViewById(R.id.RecycleView)
        addFAB = findViewById(R.id.idFloatButton)

        list = ArrayList<GroceryEntities>()
        groceryRVAdapter = GroceryRVAdapter(list, this)
        itemsRV.layoutManager = LinearLayoutManager(this)
        itemsRV.adapter = groceryRVAdapter

        val groceryRepository = GroceryRepository(GroceryDatabase.invoke(this))
        val factory = GroceryViewModelFactory(groceryRepository)

        groceryViewModel = ViewModelProvider(this, factory).get(GroceryViewModel::class.java)
        groceryViewModel.getAllGroceryItems().observe(this, Observer {
            groceryRVAdapter.list = it
            groceryRVAdapter.notifyDataSetChanged()
        })

        addFAB.setOnClickListener {
            openDialog()
        }




    }

    fun openDialog(){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.grocery_add_dialog)
        val cancelBtn = dialog.findViewById<Button>(R.id.CancelButton)
        val addBtn = dialog.findViewById<Button>(R.id.AddButton)
        val itemNameEdt = dialog.findViewById<EditText>(R.id.ItemNameAdd)
        val itemPriceEdt = dialog.findViewById<EditText>(R.id.ItemPriceAdd)
        val itemQuantityEdt = dialog.findViewById<EditText>(R.id.ItemQuantityAdd)
        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }

        addBtn.setOnClickListener {
            val itemName : String = itemNameEdt.text.toString()
            val itemPrice : String = itemPriceEdt.text.toString()
            val itemQuantity : String = itemQuantityEdt.text.toString()
            val qty:Int = itemQuantity.toInt()
            val prc: Int = itemPrice.toInt()
            if(itemName.isNotEmpty() && itemPrice.isNotEmpty() && itemQuantity.isNotEmpty()){
                val items = GroceryEntities(itemName, qty, prc)
                groceryViewModel.insert(items)
                Toast.makeText(applicationContext,"Item Added!",Toast.LENGTH_SHORT).show()
                groceryRVAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }else{
                Toast.makeText(applicationContext,"Please, enter all the data",Toast.LENGTH_SHORT).show()

            }
        }

        dialog.show()
    }

    override fun onItemClick(groceryEntities: GroceryEntities) {
        groceryViewModel.delete(groceryEntities)
        groceryRVAdapter.notifyDataSetChanged()
        Toast.makeText(applicationContext,"Item Deleted!!!",Toast.LENGTH_SHORT).show()
    }
}