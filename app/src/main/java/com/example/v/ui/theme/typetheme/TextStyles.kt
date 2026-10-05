package com.example.v.ui.theme.typetheme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
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
        fontSize = 34.sp,
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
        lineHeight = 23.sp
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
        lineHeight = 14.sp
    )
)

private fun generateTextStyle(
    oldTextStyle: TextStyle,
    newFontFamily: FontFamily?,
    addFontSp: Int,
    addLineSp: Int
): TextStyle = oldTextStyle.copy(
    fontFamily = newFontFamily ?: oldTextStyle.fontFamily,
    fontSize = (oldTextStyle.fontSize.value+addFontSp).sp,
    lineHeight = (oldTextStyle.lineHeight.value+addLineSp).sp
)

internal fun TextStyles.generateTopography(
    fontFamily: FontFamily? = null,
    addSp: Int = 0
): TextStyles{
    return this.copy(
        displayText = generateTextStyle(this.displayText,fontFamily,addSp,addSp),
        headlineText = generateTextStyle(this.headlineText,fontFamily,addSp,addSp),
        titleText = generateTextStyle(this.titleText,fontFamily,addSp,addSp),
        bodyText = generateTextStyle(this.bodyText,fontFamily,addSp,addSp),
        labelLargeText = generateTextStyle(this.labelLargeText,fontFamily,addSp,addSp),
        labelMediumText = generateTextStyle(this.labelMediumText,fontFamily,addSp,addSp),
        labelSmallTextStyle = generateTextStyle(this.labelSmallTextStyle,fontFamily,addSp,addSp)
    )
}

fun TextStyles.setFontFamily(newFontFamily: FontFamily?): TextStyles {
    return this.generateTopography(fontFamily = newFontFamily)
}

fun TextStyles.setFontSp(newSp: Int): TextStyles {
    return this.generateTopography(addSp = newSp)
}

