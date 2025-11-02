package com.example.hotsdraftadviser_kmp.enums

import kotlinx.serialization.Serializable

@Serializable
enum class RoleEnum {
    ranged,
    support,
    melee,
    heal,
    tank,
    bruiser
}