package com.ramsay.contacts_app.models

import androidx.room.Database
import androidx.room.RoomDatabase

    @Database(entities = [Contacts::class], version = 1)
    abstract class ContactsRoomDataBase : RoomDatabase() {

        abstract fun contactDAO(): ContactsDAO

    }
