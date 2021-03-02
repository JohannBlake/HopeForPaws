package dev.wirespec.hopeforpaws.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Pets")
data class PetListItemInfo (
    @PrimaryKey
    var id: Int = 0,
    var name: String = "",
    var birthdate: String = "",
    var gender: String = "",
    var color: String = "",
    var type: String = "",
    var description: String = "",
    var imageCount: Int = 1,
    var position: Int = 0
)