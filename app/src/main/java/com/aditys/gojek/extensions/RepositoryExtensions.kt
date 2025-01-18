package com.aditys.gojek.extensions

import com.aditys.gojek.model.Repository
import com.aditys.gojek.model.RepositoryEntity

fun Repository.toEntity(): RepositoryEntity {
    return RepositoryEntity(
        id = this.id,
        name = this.name,
        description = this.description,
        language = this.language,
        stars = this.stars,
        forks = this.forks
    )
}