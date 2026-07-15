package com.example.v.ui.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.w3c.dom.Text

@Composable
fun CastTextField(
    state: TextFieldState,
    secondText: String,
    lineLimits: TextFieldLineLimits,
){
    BasicTextField(
        state,
        modifier = Modifier.fillMaxWidth(),
        decorator = {
            if (state.text.isEmpty()) {
                Text(
                    text = secondText,
                    color = MaterialTheme.colorScheme.onTertiary,
                    fontSize = 23.sp
                )
            }
            it()
        },
        textStyle = TextStyle(
            fontSize = 23.sp,
            color = MaterialTheme.colorScheme.onBackground
        ),
        lineLimits = lineLimits,
        cursorBrush = SolidColor(Color(0xFF74C0FC))
    )
}