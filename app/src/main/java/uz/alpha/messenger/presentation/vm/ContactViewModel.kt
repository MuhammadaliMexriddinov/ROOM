package uz.alpha.messenger.presentation.vm

import kotlinx.coroutines.flow.SharedFlow
import uz.alpha.messenger.data.room.entity.ContactEntity

interface ContactViewModel {
    val getAllFlow : SharedFlow<List<ContactEntity>>
    val  openAddContactFlow : SharedFlow<Unit>
    fun addClick()
    fun deleteContact(contactEntity: ContactEntity)
    fun updateContact(contactEntity: ContactEntity)
    fun insertContact(contactEntity: ContactEntity)
}