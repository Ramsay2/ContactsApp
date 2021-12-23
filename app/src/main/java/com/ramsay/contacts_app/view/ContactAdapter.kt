package com.ramsay.contacts_app.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ramsay.contacts_app.R
import com.ramsay.contacts_app.databinding.ItemViewBinding
import com.ramsay.contacts_app.models.Contacts

class ContactAdapter(private val contactList: List<Contacts>, private val onContactClick: OnContactClick) :
    PagingDataAdapter<Contacts, ContactAdapter.ContactViewHolder>(diffUtil) {


    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Contacts>() {
            override fun areItemsTheSame(oldItem: Contacts, newItem: Contacts): Boolean {
                return newItem.id == oldItem.id
            }

            override fun areContentsTheSame(oldItem: Contacts, newItem: Contacts): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layoutBinding: ItemViewBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_view, parent, false)
        return ContactViewHolder(layoutBinding,onContactClick)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contactList[position]
        holder.getContact(contact)
/*
        holder.cardView.setOnClickListener {
         val count = contact.contactCount
            Log.d("TAG3456", "onBindViewHolder: $count")

            if (count != null) {
                contact.contactCount = count + 1
            }

            notifyDataSetChanged()
        }*/


    }

    override fun getItemCount(): Int {
        //   Log.d("TAG34", "onBindViewHolder: ${contactList.size}")

        return contactList.size
    }


    inner class ContactViewHolder(private val binding: ItemViewBinding, private val onContactClick: OnContactClick) :
        RecyclerView.ViewHolder(binding.root) {

       // val cardView = binding.cardView

        fun getContact(contacts: Contacts) {
            binding.contact = contacts
            binding.onClick = onContactClick

        }

    }

}