package YourSSU_Backend.Assignment.user.dto

import YourSSU_Backend.Assignment.user.model.User
import com.fasterxml.jackson.annotation.JsonProperty

class UserResponseDto(

    @JsonProperty("email")
    private val _email: String ,

    @JsonProperty("username")
    private val _username: String
) {
    val email: String
        get() = _email!!

    val username: String
        get() = _username!!

    companion object {
        fun fromEntity(user: User): UserResponseDto =
            UserResponseDto(user.email, user.username)
    }
}