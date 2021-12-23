package com.ramsay.contacts_app.view

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ramsay.contacts_app.models.Contacts

class ContactPagingSource  (private val contactList: ArrayList<Contacts>
) : PagingSource<Int, Contacts>() {


    override fun getRefreshKey(state: PagingState<Int, Contacts>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Contacts> {
        val pageNumber = params.key ?: 1
        return try {
            LoadResult.Page(
                data = contactList,
                prevKey = null,
                nextKey = if (contactList.isEmpty()) null else pageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}