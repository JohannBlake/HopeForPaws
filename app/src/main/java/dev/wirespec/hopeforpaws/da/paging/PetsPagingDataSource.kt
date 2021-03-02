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

            // IMPORTANT: The backend API has a bug in it. If you set the start position to a value greater
            // than the last item position, all the records end up getting returned. Until this bug is fixed,
            // the code below is temporary. It makes sure to limit the number of items to 60. When the backend
            // is fixed, the code below will be modified to handle paging correctly.

            // Store the position of each item out of the total items. This is needed to help
            // the grid layout to determine which column the item will appear under.
            pets.forEachIndexed { index, pet ->
                pet.position = petAPIOptions.startPos  + index
            }
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