package br.com.domain.entity

import java.io.Serializable

data class SelectionItem(
    val id: String? = null,
    val description: String? = null,
    var isSelected: Boolean = false
) : Serializable