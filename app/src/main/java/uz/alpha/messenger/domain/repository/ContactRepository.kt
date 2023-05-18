package uz.alpha.messenger.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.alpha.messenger.data.room.entity.ContactEntity

interface ContactRepository {
    suspend fun insertContact(contactEntity: ContactEntity)
    suspend fun deleteContact(contactEntity: ContactEntity)
    suspend fun updateContact(contactEntity: ContactEntity)
    fun getAllContacts(): Flow<List<ContactEntity>>

}