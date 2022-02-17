package com.example.groceryapp.Database

import androidx.room.*

@Entity(tableName = "grocery_items")
data class GroceryEntities (
    @ColumnInfo(name = "itemName")
    var itemName: String,

    @ColumnInfo(name ="itemQuantity")
    var itemQuantity: Int,

    @ColumnInfo(name = "itemPrice")
    var itemPrice: Int,
)
{
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}