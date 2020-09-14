package br.com.domain.entity


data class FirebaseResponse(
    val errorMessage: String? = null
) {

    fun isSuccess(): Boolean {
        return errorMessage.isNullOrEmpty()
    }
}