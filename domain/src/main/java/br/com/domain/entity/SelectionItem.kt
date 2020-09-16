package br.com.domain.entity

import java.io.Serializable

data class SelectionItem(
    val id: Long? = null,
    val legacyId: Long? = null,
    val description: String? = null,
    val initials: String? = null,
    val unaccentedDescription: String? = null,
    val active: Boolean? = null,
    var isSelected: Boolean = false,
    var number: Int? = null
) : Serializable