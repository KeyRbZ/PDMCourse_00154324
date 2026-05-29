package com.zelada.jsonplaceholde.data.dto

import com.zelada.jsonplaceholde.domain.model.Post
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostDto(
    @SerialName("id")
    val id: Int = 0,

    @SerialName("userId")
    val userId: Int = 0,

    @SerialName("title")
    val title: String = "",

    @SerialName("body")
    val body: String = ""
)


fun PostDto.toModel(): Post = Post(
    id = id,
    userId = userId,
    title = title,
    body = body
)


fun Post.toDto(): PostDto = PostDto(
    id = id,
    userId = userId,
    title = title,
    body = body
)
