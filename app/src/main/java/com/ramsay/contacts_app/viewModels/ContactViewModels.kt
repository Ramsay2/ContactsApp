package com.ramsay.contacts_app.viewModels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.ramsay.contacts_app.models.Contacts
import com.ramsay.contacts_app.repository.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContactViewModels
@Inject constructor(private val contactRepository: ContactRepository):ViewModel() {


    fun pagingData(): LiveData<PagingData<Contacts>> = contactRepository.getPagingData()

    suspend fun saveToDb(context: Context){
        contactRepository.saveToDB(context)

    }

    fun getContactFromDB():LiveData<List<Contacts>>{
        Log.d("TAG987", "getContactFromDB: ${contactRepository.getContacts().value}")
        return contactRepository.getContacts()
    }

    fun updateContact(contacts: Contacts){
        contactRepository.updateContacts(contacts)
    }

}