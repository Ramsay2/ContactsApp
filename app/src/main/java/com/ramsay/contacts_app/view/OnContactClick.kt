package com.ramsay.contacts_app.view

import com.ramsay.contacts_app.models.Contacts

interface OnContactClick {

    fun onClick(contacts: Contacts)
}