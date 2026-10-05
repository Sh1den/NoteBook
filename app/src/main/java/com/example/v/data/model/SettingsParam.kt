package com.example.v.data.model
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import com.example.v.R
import com.example.v.ui.theme.typetheme.FiraSansFonts
import com.example.v.ui.theme.typetheme.MontserratFonts
import com.example.v.ui.theme.typetheme.TitleFonts
import com.example.v.ui.theme.typetheme.UbuntuFonts


@Composable
fun getSecondLanguage(language: String) = when(language){
        stringResource(R.string.language_tabs_eu) -> stringResource(R.string.language_en)
        else -> stringResource(R.string.language_rus)
    }

enum class ColorTheme(
    val idTheme: Int
){
    Dark(R.string.dark_theme),
    Light(R.string.light_theme),
    Default(R.string.default_theme);

    companion object {
        fun getTheme(id: Int): ColorTheme{
            return ColorTheme.entries.find { it.idTheme == id } ?: Default
        }
    }
}
enum class GridColumn(
    val type: Int,
    val countColumn: Int
){
    ContinuesColumn(R.string.list,1),
    TwoColumn(R.string.two_column,2),
    ThreeColumn(R.string.three_column,3);
    companion object{
        fun getGridColumn(typeView: Int): GridColumn{
            return GridColumn.entries.find { it.countColumn == typeView } ?: ContinuesColumn
        }
    }
}


enum class TextStyleSpTheme(
    val value: Int,
    val currentSp: Int
) {
    Small(value = R.string.small_font,0),
    Medium(value = R.string.medium_font,1),
    Large(value = R.string.large_font,2);

    companion object {
        fun toTextSpTheme(valueType: Int): TextStyleSpTheme{
            return entries.find { it.value == valueType } ?: TextStyleSpTheme.Small
        }
    }
}

enum class TextStyleFontFamilyTheme(
    val value: Int,
    val fontFamily: FontFamily
){
    Default(R.string.default_font, FontFamily.Default),
    PlayfairDisplay(R.string.playfair_font, TitleFonts),
    Montserrat(R.string.montserrat_font, MontserratFonts),
    Ubuntu(R.string.ubuntu_font, UbuntuFonts),
    FiraSans(R.string.firasans_font, FiraSansFonts);

    companion object {
        fun toTextStyleFontFamilyTheme(valueType: Int): TextStyleFontFamilyTheme {
            return entries.find { it.value == valueType } ?: Default
        }
    }
}

data class  SettingTopography(
    val textStyleSpTheme: TextStyleSpTheme = TextStyleSpTheme.Small,
    val textStyleFontFamilyTheme: TextStyleFontFamilyTheme = TextStyleFontFamilyTheme.Default
)