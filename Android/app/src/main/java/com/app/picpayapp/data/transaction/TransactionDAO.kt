package com.app.picpayapp.data.transaction

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TransactionDAO {

    @Query("SELECT * FROM TRANSACTION")
    fun getAll(): LiveData<List<LocalTransaction>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(transaction: LocalTransaction): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(transacoes: List<LocalTransaction>)

    @Delete
    fun delete(transaction: LocalTransaction)

    @Update
    fun update(transaction: LocalTransaction)

}