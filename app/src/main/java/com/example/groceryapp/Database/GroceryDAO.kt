package com.example.groceryapp.Database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GroceryDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: GroceryEntities)

    @Delete
    fun delete(item: GroceryEntities)

    @Query("Select * from grocery_items")
    fun getAllGroceryItems(): LiveData<List<GroceryEntities>>

}