package com.lezhin.test.db.search

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

@Entity(indices = [ Index(value = ["keyword"], unique = true)])
data class Search (
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        val keyword: String
)
