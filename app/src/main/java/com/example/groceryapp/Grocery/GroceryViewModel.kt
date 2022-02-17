package com.example.groceryapp.Grocery

import androidx.lifecycle.ViewModel
import com.example.groceryapp.Database.GroceryEntities
import com.example.groceryapp.Database.GroceryRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GroceryViewModel(private val repository: GroceryRepository) : ViewModel() {

    fun insert(items: GroceryEntities) = GlobalScope.launch {
        repository.insert(items)
    }

    fun delete(items: GroceryEntities) = GlobalScope.launch {
        repository.delete(items)
    }

    fun getAllGroceryItems() = repository.getAllItems()
}