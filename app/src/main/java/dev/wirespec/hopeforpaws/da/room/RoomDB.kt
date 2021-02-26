package dev.wirespec.hopeforpaws.da.room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.wirespec.hopeforpaws.App
import dev.wirespec.hopeforpaws.models.PetListItemInfo

@Database(entities = [PetListItemInfo::class], version = 1, exportSchema = true)
abstract class RoomDB : RoomDatabase() {
    abstract fun roomDao(): RoomDao

    companion object {
        var roomDB = Room.databaseBuilder(App.context, RoomDB::class.java, "HopeForPaws").build()
    }
}