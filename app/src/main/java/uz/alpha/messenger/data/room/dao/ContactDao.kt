package uz.alpha.messenger.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import uz.alpha.messenger.data.room.entity.ContactEntity


@Dao
interface ContactDao {

    @Insert
    fun  insertContact(contactEntity: ContactEntity)

    @Update
    fun updateContact(contactEntity: ContactEntity)

    @Delete
    fun deleteContact(contactEntity: ContactEntity)

    @Query("SELECT * FROM  ContactEntity")
    fun getAll():Flow<List<ContactEntity>>


}