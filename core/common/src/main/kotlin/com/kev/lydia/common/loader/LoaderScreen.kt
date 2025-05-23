package com.kev.lydia.common.loader

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kev.aviv.common.R

@Composable
fun LoaderScreen(
    state: LoaderState,
    onRetry: () -> Unit,
    screenComposable: @Composable () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (state) {
            LoaderState.IN_PROGRESS -> CircularLoader()
            LoaderState.FAILED -> ReloadScreen(onRetry)
            LoaderState.SUCCEED -> ReloadScreen(onRetry)
        }
    }
}

@Composable
fun ReloadScreen(
    onRetry: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 20.dp,
                end = 20.dp,
                bottom = 20.dp
            ),
        verticalArrangement = Arrangement.Center
    ) {
        ThousandsPickerSeparator2()
        Image(
            painter = painterResource(
                R.drawable.ic_error
            ), contentDescription = null,
            Modifier
                // .size(150.dp)
                .align(Alignment.CenterHorizontally)
                .padding(top = 24.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.error_screen_title),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 24.sp
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.error_screen_description),
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 16.sp
        )

        Spacer(Modifier.height(40.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onClick = onRetry
        ) {
            Text(text = stringResource(R.string.error_screen_button_text))
        }

        Spacer(Modifier.height(40.dp))
    }
}

@Preview
@Composable
private fun LoaderScreenPreview() {
    LoaderScreen(
        state = LoaderState.FAILED,
        onRetry = {},
        screenComposable = {}
    )
}


