package com.ramsay.contacts_app.di

import android.content.Context
import androidx.room.Room
import com.ramsay.contacts_app.models.ContactsDAO
import com.ramsay.contacts_app.models.ContactsRoomDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ContactsRoomDataBase {
        val builder = Room.databaseBuilder(
            context,
            ContactsRoomDataBase::class.java,
            "contact_db"
        )
        builder.fallbackToDestructiveMigration()
        return builder.build()
    }

    @Provides
    @Singleton
    fun providesDao(contactDb: ContactsRoomDataBase): ContactsDAO {
        return contactDb.contactDAO()
    }
}