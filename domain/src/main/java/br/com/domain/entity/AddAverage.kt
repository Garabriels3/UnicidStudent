package br.com.domain.entity

import java.io.Serializable

data class AddAverage(
    val a1: String? = "",
    val a2: String? = "",
    val discipline: String? = "null",
    val af: String? = "null",
    val totalNote: String? = "0",
    val isAff: String? = "false"
) : Serializable