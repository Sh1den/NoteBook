package com.example.v.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.v.LocalSharedStateTheme
import com.example.v.data.model.Theme

@Composable
fun SettingEligment(
    painter: Int,
    primaryText: String,
    secondaryText: String,
    optionParam: List<String>
){
    var optionOpen by remember { mutableStateOf(false) }
        TextButton(
            modifier = Modifier.fillMaxWidth().height(70.dp),
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.onSurface
            ),
            onClick = {
                optionOpen = !optionOpen
            }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(painter = painterResource(painter), contentDescription = null)
                Spacer(Modifier.size(15.dp))
                Column() {
                    Text("${primaryText}")
                    Text(
                        text = "${secondaryText}",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    AnimatedVisibility(
        visible = optionOpen,
        enter = fadeIn(tween(500,100))+expandVertically(tween(600,100, easing = FastOutSlowInEasing)),
        exit = fadeOut(tween(500,100))+shrinkVertically(tween(600,100, easing = FastOutSlowInEasing))
    ) {
        Column() {
            var thTheme by LocalSharedStateTheme.current
            TextButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    thTheme = Theme(Theme.ColorTheme.Dark)
                }
            ) {
                Text(optionParam[0])
            }
            TextButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    thTheme = Theme(Theme.ColorTheme.Light)
                }) {
                Text(optionParam[1])
            }
        }
    }

}