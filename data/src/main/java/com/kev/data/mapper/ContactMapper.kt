package com.kev.data.mapper

import com.kev.data.datasource.local.ContactEntity
import com.kev.data.model.ContactDto
import com.kev.domain.model.Contact

object ContactMapper {

    fun ContactDto.toContactEntity(): ContactEntity {
        return ContactEntity(
            id = login.uuid,
            fullName = name.first + " " + name.last,
            email = email,
            phone = phone,
            avatarUrl = picture.large,
            country = location.country,
            city = location.city,
        )
    }

    fun ContactEntity.toDomain(): Contact {
        return Contact(
            id = id,
            fullName = fullName,
            email = email,
            phone = phone,
            avatarUrl = avatarUrl,
            country = country,
            city = city,
        )
    }
}