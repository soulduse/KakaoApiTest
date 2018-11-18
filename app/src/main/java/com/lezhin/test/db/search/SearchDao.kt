package com.lezhin.test.db.search

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface SearchDao {
    @Insert
    fun insertSearch(search: Search)

    @Query("DELETE FROM search")
    fun deleteAll()

    @Query("SELECT keyword FROM search ORDER BY id DESC LIMIT 15")
    fun findAllKeywords(): LiveData<List<String>>

    @Query("SELECT * FROM search")
    fun findAll(): List<Search>

    @Query("SELECT keyword FROM search WHERE keyword = :keyword")
    fun findsByKeyword(keyword: String): List<String>
}
