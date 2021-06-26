package com.invillia.todolist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Task(
    @PrimaryKey  val id: Int = 0,

    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "hour")
    val hour: String
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Task

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }
}

