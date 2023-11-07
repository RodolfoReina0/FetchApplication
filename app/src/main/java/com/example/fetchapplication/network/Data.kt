package com.example.fetchapplication.network
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data (
    @SerialName("id")
    var id: Int,

    @SerialName("listId")
    var listId: Int,

    @SerialName("name")
    var name: String?,
)