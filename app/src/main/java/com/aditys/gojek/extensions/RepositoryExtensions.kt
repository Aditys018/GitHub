package com.aditys.gojek.extensions

import com.aditys.gojek.model.DataClassRepo
import com.aditys.gojek.model.RepositoryEntity

fun DataClassRepo.toEntity(): RepositoryEntity {
    return RepositoryEntity(
        id = this.id,
        name = this.name,
        description = this.description,
        language = this.language,
        stars = this.stargazers_count,
        forks = this.forks_count ,
        imageUrl = this.owner.avatar_url
    )
}