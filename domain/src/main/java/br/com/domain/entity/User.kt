package br.com.domain.entity

data class User(
    val name: String,
    val rgm: String,
    val courseName: String,
    val group: String,
    val banana: String,
    val subTest: SubTest
)

data class SubTest(
    val teste: String,
    val listNota: List<String>
)