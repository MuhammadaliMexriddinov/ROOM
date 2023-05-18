package uz.alpha.messenger.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.alpha.messenger.data.room.dao.ContactDao
import uz.alpha.messenger.data.room.entity.ContactEntity

@Database(entities = [ContactEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun contactDao(): ContactDao

    companion object {
        private lateinit var instance: AppDatabase

        fun init(ctx: Context) {
            if(!Companion::instance.isInitialized){
                instance = Room.databaseBuilder(ctx, AppDatabase::class.java , "contact.db")
                    .allowMainThreadQueries()
                    .build()
            }
        }

        fun getInstance() = instance
    }
}