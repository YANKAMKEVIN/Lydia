package com.kev.presentation

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kev.domain.model.Contact
import com.kev.domain.usecase.GetContactsListUseCase
import com.kev.lydia.common.base.BaseViewModel
import com.kev.lydia.common.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    private val getContactsListUseCase: GetContactsListUseCase
) : BaseViewModel<ContactState>() {
    override fun createInitialState(): ContactState = ContactState()


    private val _moviesState: MutableStateFlow<PagingData<Contact>> =
        MutableStateFlow(value = PagingData.empty())
    val moviesState: MutableStateFlow<PagingData<Contact>> get() = _moviesState

    init {
        onEvent(HomeEvent.GetHome)
    }

    fun onEvent(event: HomeEvent) {
        viewModelScope.launch {
            when (event) {
                is HomeEvent.GetHome -> {
                    getMovies()
                }
            }
        }
    }

    private suspend fun getMovies() {
        getContactsListUseCase()
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect {
                _moviesState.value = it
            }
    }


}

sealed class HomeEvent {
    data object GetHome : HomeEvent()
}

data class ContactState(
    val contacts: List<Contact> = emptyList(),
) : UiState