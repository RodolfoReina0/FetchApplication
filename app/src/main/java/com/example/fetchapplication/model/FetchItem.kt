package com.example.fetchapplication.model
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FetchItem (
    @SerialName("id")
    var id: Int,

    @SerialName("listId")
    var listId: Int,

    @SerialName("name")
    var name: String?,
)