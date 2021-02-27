package dev.wirespec.hopeforpaws.da.paging


import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.wirespec.hopeforpaws.da.web.HopeForPawsWebAPI
import dev.wirespec.hopeforpaws.da.web.PetAPIConfig
import dev.wirespec.hopeforpaws.da.web.PetAPIOptions
import dev.wirespec.hopeforpaws.models.PetListItemInfo

class PetsPagingDataSource(val webApi: HopeForPawsWebAPI, val petAPIOptions: PetAPIOptions) : PagingSource<Int, PetListItemInfo>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PetListItemInfo> {
        val startPos = params.key ?: 0

        return try {
            val pets = webApi.getPets(startPos, petAPIOptions.pageSize, if (petAPIOptions.sortDesc) "desc" else "asc")

            var nextStartPos: Int? = null

            if (pets.size == PetAPIConfig.PAGING_SIZE) {
                nextStartPos = startPos + PetAPIConfig.PAGING_SIZE
            }

            LoadResult.Page(
                data = pets,
                prevKey = null,
                nextKey = nextStartPos
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PetListItemInfo>): Int = 1
}