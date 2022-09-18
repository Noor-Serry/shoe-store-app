package noor.serry.shoestoreapp.data.room

import android.content.Context
import androidx.room.Database
import noor.serry.shoestoreapp.data.room.UserInfo
import noor.serry.shoestoreapp.data.room.ShoesInfo
import androidx.room.RoomDatabase
import noor.serry.shoestoreapp.data.room.RoomDB
import kotlin.jvm.Synchronized
import androidx.room.Room

@Database(entities = [UserInfo::class, ShoesInfo::class], version = 8)
abstract class RoomDB : RoomDatabase() {
    abstract fun dao(): Dao?

    companion object {
        private var instance: RoomDB? = null
        @Synchronized
        fun getInstance(context: Context): RoomDB? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDB::class.java,
                    "AlarmDataBase"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
            }
            return instance
        }
    }
}