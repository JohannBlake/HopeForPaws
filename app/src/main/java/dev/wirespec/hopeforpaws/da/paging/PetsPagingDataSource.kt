package dev.wirespec.hopeforpaws.da.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.wirespec.hopeforpaws.da.Repository
import dev.wirespec.hopeforpaws.da.web.PetAPIConfig
import dev.wirespec.hopeforpaws.da.web.PetAPIOptions
import dev.wirespec.hopeforpaws.models.PetListItemInfo

class PetsPagingDataSource(private val petAPIOptions: PetAPIOptions) : PagingSource<Int, PetListItemInfo>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PetListItemInfo> {
        var startPos = params.key ?: 0
        petAPIOptions.startPos = startPos * PetAPIConfig.PAGING_SIZE

        return try {
            var pets = Repository.getPets(petAPIOptions)

//            var prevKey = if (startPos > 0) startPos - PetAPIConfig.PAGING_SIZE else null
//            var nextKey = if (pets.isNotEmpty()) startPos + PetAPIConfig.PAGING_SIZE else null
            var prevKey: Int? = null
            var nextKey: Int? = null

            if (startPos == 3) {
                prevKey = 2
                nextKey = null
                pets = mutableListOf()
            } else {
                prevKey = if (startPos > 0) startPos - 1 else null // PetAPIConfig.PAGING_SIZE else null
                nextKey = if (pets.isNotEmpty()) startPos + 1 else null // PetAPIConfig.PAGING_SIZE else null
            }

            LoadResult.Page(
                data = pets,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PetListItemInfo>): Int = 0
}