package com.dicoding.doaseharihari.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class DoaEntity(
    @ColumnInfo("id_item")
    @PrimaryKey(autoGenerate = true)
    val idItem: Int? = null,

    @ColumnInfo("id")
    val id: Long,

    @ColumnInfo("title")
    val title: String,

    @ColumnInfo("arab")
    val arab: String,

    @ColumnInfo("latin")
    val latin: String,

    @ColumnInfo("translation")
    val translation: String
)