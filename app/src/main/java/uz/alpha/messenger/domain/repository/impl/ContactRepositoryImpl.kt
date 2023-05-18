package uz.alpha.messenger.domain.repository.impl

import kotlinx.coroutines.flow.Flow
import uz.alpha.messenger.data.room.AppDatabase
import uz.alpha.messenger.data.room.entity.ContactEntity
import uz.alpha.messenger.domain.repository.ContactRepository

class ContactRepositoryImpl:ContactRepository {

    private  val  database= AppDatabase.getInstance().contactDao()


    override suspend fun insertContact(contactEntity: ContactEntity) {
        database.insertContact(contactEntity)
    }
    override suspend fun deleteContact(contactEntity: ContactEntity) {
        database.deleteContact(contactEntity)
    }
    override suspend fun updateContact(contactEntity: ContactEntity) {
        database.updateContact(contactEntity)
    }
    override fun getAllContacts(): Flow<List<ContactEntity>> =  database.getAll()
}