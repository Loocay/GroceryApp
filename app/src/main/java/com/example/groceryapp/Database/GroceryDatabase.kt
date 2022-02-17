package com.example.groceryapp.Database

import android.content.Context
import androidx.room.*

@Database(entities = [GroceryEntities::class], version = 1)
abstract class GroceryDatabase : RoomDatabase(){

    abstract fun getGroceryDao() : GroceryDAO

    companion object{
        @Volatile
        private var instance: GroceryDatabase? = null
        private var LOCK =Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context)=
            Room.databaseBuilder(
                context.applicationContext,
                GroceryDatabase::class.java,
                "Grocery.db"
            ).build()

    }

    }