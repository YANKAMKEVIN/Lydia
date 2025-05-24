package com.kev.data.mapper

import com.kev.data.model.ContactDto
import com.kev.domain.model.Contact

object ContactMapper {

    //Map ContactDTO to ContactDomain
    fun ContactDto.toDomain(): Contact {
        return Contact(
            fullName = name.first + " " + name.last,
            email = email,
            phone = phone,
            avatarUrl = picture.large,
            country = location.country,
            city = location.city,
        )
    }
}