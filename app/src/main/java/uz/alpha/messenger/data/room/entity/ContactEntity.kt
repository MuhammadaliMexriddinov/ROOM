package uz.alpha.messenger.data.room.entity

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ContactEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val fullName: String,
    val date: String,
    val phone:String
)