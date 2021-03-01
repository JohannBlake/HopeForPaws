package dev.wirespec.hopeforpaws.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Pets")
data class PetListItemInfo (
    @PrimaryKey
    var id: Int = 0,
    var name: String = "",
    var genderId: Int = 0,
    var age: Int = 0,
    var photoUrl: String = "",
    var location: String = "",
    var position: Int = 0
)