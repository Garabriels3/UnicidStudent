package br.com.domain.entity


data class FirebaseResponse(
    val errorMessage: String? = null,
    val userUid: String? = null,
    val item: Any? = null
) {

    fun isSuccess(): Boolean {
        return errorMessage.isNullOrEmpty()
    }
}