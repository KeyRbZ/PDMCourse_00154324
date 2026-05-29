package com.zelada.jsonplaceholde.data.repository

import com.zelada.jsonplaceholde.domain.model.Post

interface PostRepository {
    suspend fun getPosts(): Result<List<Post>>
    suspend fun createPost(post: Post): Result<Post>
}

