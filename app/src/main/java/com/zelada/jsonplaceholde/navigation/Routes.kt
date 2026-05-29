package com.zelada.jsonplaceholde.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

// sealed class type-safe para la navegación con Navigation 3
sealed class Routes : NavKey {

    @Serializable
    data object Home : Routes()

    @Serializable
    data class Detail(val postId: Int) : Routes()
}
