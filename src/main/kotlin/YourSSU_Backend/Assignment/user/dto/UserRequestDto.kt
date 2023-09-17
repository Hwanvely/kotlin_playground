package YourSSU_Backend.Assignment.user.dto

import com.fasterxml.jackson.annotation.JsonProperty

import YourSSU_Backend.Assignment.user.model.User
import javax.validation.constraints.NotBlank


data class UserRequestDto(

    @field:NotBlank
    @JsonProperty("email")
    private val _email: String ,

    @field:NotBlank
    @JsonProperty("password")
    private val _password: String,

    @field:NotBlank
    @JsonProperty("username")
    private val _username: String
) {
    val email: String
        get() = _email!!
    val password: String
        get() = _password!!
    val username: String
        get() = _username!!

    fun toEntity(): User =
        User(null, email, password, username)
}