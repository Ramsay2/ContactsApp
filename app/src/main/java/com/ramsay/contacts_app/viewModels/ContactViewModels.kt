package com.ramsay.contacts_app.viewModels

import android.content.Context
import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ramsay.contacts_app.models.Contacts
import com.ramsay.contacts_app.repository.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContactViewModels @Inject constructor(private val contactRepository: ContactRepository):ViewModel() {


    fun pagingData(contacts:ArrayList<ContactsContract.Contacts>)
    = contactRepository.getPagingData()

    suspend fun saveToDb(context: Context){
        contactRepository.saveToDB(context)

    }

    fun getContactFromDB(contacts: Contacts):LiveData<List<Contacts>>{
        return contactRepository.getContacts()
    }

    fun updateContact(contacts: Contacts){
        contactRepository.updateContacts(contacts)
    }

}