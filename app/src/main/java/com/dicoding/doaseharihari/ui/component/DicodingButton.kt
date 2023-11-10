package com.dicoding.doaseharihari.ui.component

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.dicoding.doaseharihari.ui.theme.DoaSehariHariTheme


@Composable
fun DicodingButton(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val intent =
        remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/irfan-efendi19")) }
    Button(
        onClick = { context.startActivity(intent) },
        modifier = modifier.wrapContentSize(),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
    ) {
        Text(text = "Dicoding Profile", color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}


@Composable
@Preview(showBackground = true)
fun DicodingButtonPreview() {
    DoaSehariHariTheme {
        DicodingButton()
    }
}