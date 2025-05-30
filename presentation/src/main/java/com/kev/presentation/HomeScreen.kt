package com.kev.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kev.domain.model.Contact
import com.kev.presentation.details.ContactDetailScreen
import com.kev.presentation.list.ContactListRoute
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
    val scope = rememberCoroutineScope()

    NavigableListDetailPaneScaffold(
        modifier = modifier,
        navigator = navigator,
        listPane = {
            ContactListRoute(
                onNavigateToDetail = { contact ->
                    scope.launch {
                        navigator.navigateTo(
                            pane = ListDetailPaneScaffoldRole.Detail,
                            contentKey = contact
                        )
                    }
                }
            )
        },
        detailPane = {
            val content = navigator.currentDestination?.contentKey as Contact?
            AnimatedPane {
                if (content != null)
                    ContactDetailScreen(contact = content, onNavigateBack = {
                        scope.launch {
                            navigator.navigateBack()
                        }
                    })
                else
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.primaryContainer),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Select an item")
                    }
            }
        },
    )
}