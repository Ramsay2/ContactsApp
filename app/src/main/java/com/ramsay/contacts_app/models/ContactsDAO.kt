package com.ramsay.contacts_app.models

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ContactsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDataInDatabase(contactDTO: List<Contacts>)

    @Query("select * from contacts order by count")
    fun getAllDatFromDb(): LiveData<List<Contacts>>

    @Update
    fun updateContactOfDB(contacts: Contacts)

    @Query("DELETE FROM contacts")
    fun deleteContact()
}