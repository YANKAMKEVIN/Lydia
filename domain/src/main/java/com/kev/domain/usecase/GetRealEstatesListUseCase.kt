package com.kev.domain.usecase

import com.kev.domain.model.Contact
import kotlinx.coroutines.flow.Flow

fun interface GetContactsListUseCase {
    suspend operator fun invoke(page: Int): Flow<List<Contact>>
}