package com.example.module2

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

// вариант 1 зеленый курсив по центру
@Composable
fun GreenItalicText(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.compose_description),
        modifier = modifier.fillMaxWidth(),
        color = Color.Green,
        fontSize = 16.sp,
        fontStyle = FontStyle.Italic,
        textAlign = TextAlign.Center
    )
}

// вариант 2 одна строка и многоточие в конце
@Composable
fun OneLineText(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.compose_description),
        modifier = modifier,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

// вариант 3 отступ первой строки фон и подчеркивание
@Composable
fun IndentedText(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.compose_description),
        modifier = modifier,
        color = Color.Black,
        fontSize = 24.sp,
        style = TextStyle(
            textIndent = TextIndent(firstLine = 48.sp),
            background = Color.Green,
            textDecoration = TextDecoration.Underline
        )
    )
}

@Preview(showBackground = true)
@Composable
fun GreenItalicTextPreview() {
    GreenItalicText()
}

@Preview(showBackground = true)
@Composable
fun OneLineTextPreview() {
    OneLineText()
}

@Preview(showBackground = true)
@Composable
fun IndentedTextPreview() {
    IndentedText()
}
