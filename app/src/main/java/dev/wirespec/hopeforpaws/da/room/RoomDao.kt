package dev.wirespec.hopeforpaws.da.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import dev.wirespec.hopeforpaws.models.PetListItemInfo

@Dao
interface RoomDao {
    @Query("SELECT * FROM Pets")
    suspend fun getPets(): List<PetListItemInfo>

    @Insert(onConflict = REPLACE)
    suspend fun storePets(pets: List<PetListItemInfo>)
}