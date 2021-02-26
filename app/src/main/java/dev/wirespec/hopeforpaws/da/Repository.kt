package dev.wirespec.hopeforpaws.da

import dev.wirespec.hopeforpaws.da.room.RoomDB
import dev.wirespec.hopeforpaws.da.web.HopeForPawsWebAPI
import dev.wirespec.hopeforpaws.da.web.RetrofitClient
import dev.wirespec.hopeforpaws.models.PetListItemInfo

class Repository {
    companion object {
        private val roomDao = RoomDB.roomDB.roomDao()
        private var webApi: HopeForPawsWebAPI = RetrofitClient.createRetrofitClient()

        suspend fun getCachedPets(projectId: String): List<PetListItemInfo> {
            return roomDao.getPets()
        }
    }
}