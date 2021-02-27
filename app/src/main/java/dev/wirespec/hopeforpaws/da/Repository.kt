package dev.wirespec.hopeforpaws.da

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dev.wirespec.hopeforpaws.da.paging.PetsPagingDataSource
import dev.wirespec.hopeforpaws.da.room.RoomDB
import dev.wirespec.hopeforpaws.da.web.HopeForPawsWebAPI
import dev.wirespec.hopeforpaws.da.web.PetAPIOptions
import dev.wirespec.hopeforpaws.da.web.RetrofitClient
import dev.wirespec.hopeforpaws.models.PetListItemInfo
import kotlinx.coroutines.flow.Flow

class Repository {
    companion object {
        private val roomDao = RoomDB.roomDB.roomDao()
        private var webApi: HopeForPawsWebAPI = RetrofitClient.createRetrofitClient()

        suspend fun getCachedPets(projectId: String): List<PetListItemInfo> {
            return roomDao.getPets()
        }

        suspend fun getPetsFromServer(options: PetAPIOptions): List<PetListItemInfo> {
            return webApi.getPets(startPos = options.startPos, options.pageSize, if (options.sortDesc) "desc" else "asc")
        }

        suspend fun getPets(options: PetAPIOptions): Flow<PagingData<PetListItemInfo>> = Pager(
            config = PagingConfig(pageSize = options.pageSize, prefetchDistance = 2),
            pagingSourceFactory = { PetsPagingDataSource(webApi, options) }
        ).flow
    }
}