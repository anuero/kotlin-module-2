package com.example.module2

import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class Contact(
    val name: String,
    val patronymic: String,
    val surname: String,
    val phone: String?,
    val address: String
)

val sampleContacts = listOf(
    Contact(
        "Евгений", "Андреевич", "Лукашин", "+7 495 495 95 95",
        "г. Москва, 3-я улица Строителей, д. 25, кв. 12"
    ),
    Contact(
        "Василий", "Егорович", "Кузякин", null,
        "Ивановская область, дер. Крутово, д. 4"
    ),
    Contact(
        "Людмила", "Прокофьевна", "Калугина", "+7 495 788 78 78",
        "Москва, Большая Никитская, д. 43, кв. 290"
    )
)

// пять строк с данными контакта кладутся в тот контейнер где их вызвали
@Composable
private fun ContactTexts(contact: Contact) {
    Text(text = "Имя: ${contact.name}")
    Text(text = "Отчество: ${contact.patronymic}")
    Text(text = "Фамилия: ${contact.surname}")
    // если телефона нет рисуем прочерк
    Text(text = "Мобильный телефон: ${contact.phone ?: "---"}")
    Text(text = "Адрес: ${contact.address}")
}

// column из первой картинки просто строки друг под другом
@Composable
fun ContactColumn(contact: Contact, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        ContactTexts(contact)
    }
}

// карточка серая и текст по центру
@Composable
fun ContactCard(contact: Contact, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ContactTexts(contact)
    }
}

// column из карточек с промежутками
@Composable
fun ContactList(contacts: List<Contact>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        contacts.forEach { ContactCard(it) }
    }
}

// row текст слева и картинка справа
@Composable
fun ContactRow(contact: Contact, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // weight отдает тексту все место кроме иконки
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ContactTexts(contact)
        }
        Image(
            painter = painterResource(R.drawable.star),
            contentDescription = "Избранное",
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview(name = "portrait", showSystemUi = true)
@Composable
fun ContactColumnPreview() {
    // отступ сверху нужен потому что в превью строка состояния рисуется поверх контента
    Box(modifier = Modifier.padding(top = 32.dp)) {
        ContactColumn(sampleContacts[0])
    }
}

@Preview(name = "portrait", showSystemUi = true)
@Composable
fun ContactListPreview() {
    // отступ сверху нужен потому что в превью строка состояния рисуется поверх контента
    Box(modifier = Modifier.padding(top = 32.dp)) {
        ContactList(sampleContacts)
    }
}

@Preview(name = "portrait", showSystemUi = true)
@Composable
fun ContactRowPreview() {
    // отступ сверху нужен потому что в превью строка состояния рисуется поверх контента
    Box(modifier = Modifier.padding(top = 32.dp)) {
        ContactRow(sampleContacts[0])
    }
}
