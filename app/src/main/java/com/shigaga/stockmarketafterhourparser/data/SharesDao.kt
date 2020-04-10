package com.shigaga.stockmarketafterhourparser.data

import androidx.paging.DataSource
import androidx.room.*

/**
 * Database Access Object for the Shares database.
 */
@Dao
interface SharesDao {
    /**
     * Room knows how to return a LivePagedListProvider, from which we can get a LiveData and serve
     * it back to UI via ViewModel.
     */
    @Query("SELECT * FROM Shares ORDER BY id")
    fun allSharesById(): DataSource.Factory<Int, Shares>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(shares: List<Shares>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(shares: Shares)

    @Delete
    fun delete(shares: Shares)

    @Query("DELETE FROM Shares")
    fun deleteAllShareData()
}