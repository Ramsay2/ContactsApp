package com.ramsay.contacts_app.models


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class Contacts(
    @ColumnInfo(name = "name")
    var contactName: String,
    @ColumnInfo(name = "number")
    var contactPhoneNumber: String,
    @ColumnInfo(name = "count")
    var contactCount: Int? = 0,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}