package com.example.v.data.model
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.v.R
import org.intellij.lang.annotations.Language


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