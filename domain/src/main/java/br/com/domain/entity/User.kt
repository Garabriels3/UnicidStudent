package br.com.domain.entity

data class User(
    var id: String,
    var email: String,
    var name: String,
    var rgm: String,
    var courseName: String,
    var semester: String
)

data class SubTest(
    val teste: String,
    val listNota: List<String>
)