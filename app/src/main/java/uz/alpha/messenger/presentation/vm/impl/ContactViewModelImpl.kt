package uz.alpha.messenger.presentation.vm.impl

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.alpha.messenger.data.room.entity.ContactEntity
import uz.alpha.messenger.domain.repository.impl.ContactRepositoryImpl
import uz.alpha.messenger.presentation.vm.ContactViewModel

class ContactViewModelImpl : ContactViewModel, ViewModel() {

    private val repo = ContactRepositoryImpl()

    override val getAllFlow = MutableSharedFlow<List<ContactEntity>>()
    override val openAddContactFlow = MutableSharedFlow<Unit>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getAllContacts().collectLatest {
                getAllFlow.emit(it)
            }
        }
    }

    override fun addClick() {
        viewModelScope.launch {
            openAddContactFlow.emit(Unit)
        }
    }

    override fun deleteContact(contactEntity: ContactEntity) {
        viewModelScope.launch {
            repo.deleteContact(contactEntity)
        }
    }

    override fun updateContact(contactEntity: ContactEntity) {
       viewModelScope.launch {
           repo.updateContact(contactEntity)
       }
    }

    override fun insertContact(contactEntity: ContactEntity) {
        viewModelScope.launch {
            repo.insertContact(contactEntity)
        }
    }
}