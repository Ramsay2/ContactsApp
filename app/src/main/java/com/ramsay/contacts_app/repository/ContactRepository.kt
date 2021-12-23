package com.ramsay.contacts_app.repository

import android.content.Context
import android.database.Cursor
import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.ramsay.contacts_app.models.Contacts
import com.ramsay.contacts_app.models.ContactsDAO
import com.ramsay.contacts_app.view.ContactPagingSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class ContactRepository @Inject constructor(private val contactsDAO: ContactsDAO) {

    private val contacts = ArrayList<Contacts>()
    fun getPagingData() = Pager(
        config = PagingConfig(
            pageSize = 20
        ),
        pagingSourceFactory = {
            ContactPagingSource(contacts)

        }
    ).liveData


    suspend fun saveToDB(context: Context) {

        val cursor: Cursor? = context.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null, null, null,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
        )
        if ((cursor?.count ?: 0) > 0) {
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    val name: Int = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
                    val number: Int =
                        cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                    val contact = Contacts(
                        cursor.getString(name),
                        cursor.getString(number)
                    )
                    contacts.add(contact)
                }
            }
        }
        CoroutineScope(Dispatchers.IO).launch {
            contactsDAO.deleteContact()
        }

        contactsDAO.insertDataInDatabase(contacts)

        cursor?.close()

    }

    fun getContacts(): LiveData<List<Contacts>> {
       // Log.d("TAG987", "saveToDB: ${contactsDAO.getAllDatFromDb().value}")
        return contactsDAO.getAllDatFromDb()
    }

    fun updateContacts(contacts:Contacts) {
        return contactsDAO.updateContactOfDB(contacts)
    }


}