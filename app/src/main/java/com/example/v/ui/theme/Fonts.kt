package com.example.v.ui.theme

import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import com.example.v.R
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

val TitleFonts = FontFamily(
    Font(R.font.quicksand_regular, weight = FontWeight.Normal),
    Font(R.font.quicksand_medium, weight = FontWeight.Medium),
    Font(R.font.quicksand_bold  , weight = FontWeight.Bold),
    Font(R.font.quicksand_semibold, weight = FontWeight.SemiBold),
    Font(R.font.quicksand_light, weight = FontWeight.Light)

)

val ComponentFonts = FontFamily(
    Font(R.font.playfairdisplay_regular, weight = FontWeight.Normal),
    Font(R.font.playfairdisplay_italic, weight = FontWeight.Normal, style = FontStyle.Italic),
    Font(R.font.playfairdisplay_medium, weight = FontWeight.Medium),
    Font(R.font.playfairdisplay_mediumitalic, weight = FontWeight.Medium, style = FontStyle.Italic),
    Font(R.font.playfairdisplay_bold, weight = FontWeight.Bold),
    Font(R.font.playfairdisplay_bolditalic, weight = FontWeight.Bold, style = FontStyle.Italic),
    Font(R.font.playfairdisplay_semibold, weight = FontWeight.SemiBold),
    Font(R.font.playfairdisplay_semibolditalic, weight = FontWeight.SemiBold, style = FontStyle.Italic),
    Font(R.font.playfairdisplay_extrabold, weight = FontWeight.ExtraBold),
    Font(R.font.playfairdisplay_extrabolditalic, weight = FontWeight.ExtraBold, style = FontStyle.Italic),
    Font(R.font.playfairdisplay_black, weight = FontWeight.Black),
    Font(R.font.playfairdisplay_blackitalic, weight = FontWeight.Black, style = FontStyle.Italic)
)