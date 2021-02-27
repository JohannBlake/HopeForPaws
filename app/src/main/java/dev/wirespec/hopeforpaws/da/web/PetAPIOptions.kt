package dev.wirespec.hopeforpaws.da.web

data class PetAPIOptions (
    var startPos: Int = 0,
    var pageSize: Int = PetAPIConfig.PAGING_SIZE,
    var sortDesc: Boolean = false
)

class PetAPIConfig {
    companion object {
        const val PAGING_SIZE = 20
    }
}

