package com.ramsay.contacts_app.view

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.ramsay.contacts_app.databinding.ActivityMainBinding
import com.ramsay.contacts_app.models.Contacts
import com.ramsay.contacts_app.viewModels.ContactViewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnContactClick {

    private lateinit var binding: ActivityMainBinding
    private lateinit var contactAdapter: ContactAdapter
    private var contactList = mutableListOf<Contacts>()
    private val contactViewModel: ContactViewModels by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        runTimePermission()


    }



    private fun runTimePermission() {
        Dexter.withContext(this).withPermissions(
            Manifest.permission.READ_CONTACTS
        ).withListener(object : MultiplePermissionsListener {
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onPermissionsChecked(multiplePermissionsReport: MultiplePermissionsReport) {


                CoroutineScope(Dispatchers.Main).launch {
                    contactViewModel.saveToDb(applicationContext)

                }
                setRecyclerview()
                getContact()

            }

            override fun onPermissionRationaleShouldBeShown(
                p0: MutableList<com.karumi.dexter.listener.PermissionRequest>?,
                permissionToken: PermissionToken?
            ) {
                permissionToken?.continuePermissionRequest()
            }
        }).check()
    }

    private fun setRecyclerview() {
        contactAdapter = ContactAdapter(contactList, this)
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = contactAdapter
        }
    }

    private fun getContact() {

       contactViewModel.getContactFromDB().observe(this, { it ->
            contactList.clear()
            contactList.addAll(it)
          //  contactList.sortBy { it.contactCount }
            contactAdapter.notifyDataSetChanged()
            Log.d("TAG1234", "getContact: $contactList")

        })


    }

    override fun onClick(contacts: Contacts) {

         val count = contacts.contactCount

         if (count != null) {
             contacts.contactCount = count + 1
         }
        CoroutineScope(Dispatchers.IO).launch {
            contactViewModel.updateContact(contacts)
        }




    }

}