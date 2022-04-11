package com.app.picpayapp.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.picpayapp.data.transaction.LocalTransaction
import com.app.picpayapp.data.transaction.TransactionDAO

@Database(
    version = 1,
    entities = [LocalTransaction::class],
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun transactionDAO(): TransactionDAO

    companion object {
        private const val NAME_DATABASE = "picpayclone.db"

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, NAME_DATABASE)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}