package dev.wirespec.hopeforpaws.da

import dev.wirespec.hopeforpaws.da.web.HopeForPawsWebAPI
import dev.wirespec.hopeforpaws.da.web.PetAPIOptions
import dev.wirespec.hopeforpaws.da.web.RetrofitClient
import dev.wirespec.hopeforpaws.models.PetListItemInfo

class Repository {
    companion object {
        private var webApi: HopeForPawsWebAPI = RetrofitClient.createRetrofitClient()

        suspend fun getPets(options: PetAPIOptions): List<PetListItemInfo> {
            return webApi.getPets(startPos = options.startPos, options.pageSize, if (options.sortDesc) "desc" else "asc")
        }
    }
}