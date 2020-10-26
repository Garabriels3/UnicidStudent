package br.com.domain.entity

import java.io.Serializable

data class AddAverage(
    val a1: String? = "",
    val a2: String? = "",
    val discipline: String? = "null",
    val afNote: String? = "null",
    val totalNote: String? = "0",
    val afState: Boolean? = false,
    val reproveState: Boolean? = false,
    val approveState: Boolean? = false
) : Serializable