package com.example.ejercicio01_compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@androidx.compose.runtime.Composable
fun ArticleScreen() {
    androidx.compose.foundation.layout.Column {

        androidx.compose.foundation.Image(
            painter = androidx.compose.ui.res.painterResource(R.drawable.bg_compose_background),
            contentDescription = null,
            modifier = androidx.compose.ui.Modifier.Companion.fillMaxWidth()
        )

        androidx.compose.material3.Text(
            text = androidx.compose.ui.res.stringResource(R.string.article_title),
            fontSize = 24.sp,
            modifier = androidx.compose.ui.Modifier.Companion.padding(16.dp)
        )

        androidx.compose.material3.Text(
            text = androidx.compose.ui.res.stringResource(R.string.article_paragraph1),
            textAlign = androidx.compose.ui.text.style.TextAlign.Companion.Justify,
            modifier = androidx.compose.ui.Modifier.Companion.padding(start = 16.dp, end = 16.dp)
        )

        androidx.compose.material3.Text(
            text = androidx.compose.ui.res.stringResource(R.string.article_paragraph2),
            textAlign = androidx.compose.ui.text.style.TextAlign.Companion.Justify,
            modifier = androidx.compose.ui.Modifier.Companion.padding(16.dp)
        )
    }
}