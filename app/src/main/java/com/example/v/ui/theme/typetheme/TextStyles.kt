package com.example.v.ui.theme.typetheme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

data class TextStyles(
    val displayText: TextStyle,
    val headlineText: TextStyle,
    val titleText: TextStyle,
    val bodyText: TextStyle,
    val labelLargeText: TextStyle,
    val labelMediumText: TextStyle,
    val labelSmallTextStyle: TextStyle
)

val MainTopography = TextStyles(
    displayText = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 32.sp,
        lineHeight = 44.sp
    ),
    headlineText = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 30.sp,
    ),
    titleText = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp
    ),
    bodyText = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp,
        lineHeight = 24.sp
    ),
    labelLargeText = TextStyle(
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
        lineHeight = 18.sp
    ),
    labelMediumText = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 18.sp
    ),
    labelSmallTextStyle = TextStyle(
        fontWeight = FontWeight.Light,
        fontSize = 10.sp,
        lineHeight = 15.sp
    )
)