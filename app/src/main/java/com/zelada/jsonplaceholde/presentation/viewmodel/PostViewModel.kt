package com.zelada.jsonplaceholde.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zelada.jsonplaceholde.data.repository.PostApiRepository
import com.zelada.jsonplaceholde.data.repository.PostRepository
import com.zelada.jsonplaceholde.domain.model.Post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class PostUiState(
    val posts: List<Post> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class PostViewModel(
    private val repository: PostRepository = PostApiRepository()
) : ViewModel() {

    private val _state = MutableStateFlow(PostUiState())
    val state: StateFlow<PostUiState> = _state.asStateFlow()

    fun loadPosts() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            repository.getPosts()
                .onSuccess { posts ->
                    _state.update { it.copy(posts = posts, isLoading = false) }
                }
                .onFailure { throwable ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = throwable.message ?: "Error desconocido"
                        )
                    }
                }
        }
    }

    fun createPost(title: String, body: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            val newPost = Post(id = 0, userId = 1, title = title, body = body)
            repository.createPost(newPost)
                .onSuccess { createdPost ->

                    _state.update {
                        it.copy(
                            posts = listOf(createdPost) + it.posts,
                            isLoading = false
                        )
                    }
                }
                .onFailure { throwable ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = throwable.message ?: "Error al crear el post"
                        )
                    }
                }
        }
    }
}

