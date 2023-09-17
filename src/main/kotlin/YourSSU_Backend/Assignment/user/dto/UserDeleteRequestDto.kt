package YourSSU_Backend.Assignment.user.dto

import YourSSU_Backend.Assignment.user.model.User
import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotBlank

class UserDeleteRequestDto(

    @field:NotBlank
    @JsonProperty("email")
    private val _email: String ,

    @field:NotBlank
    @JsonProperty("password")
    private val _password: String

) {
    val email: String
        get() = _email!!
    val password: String
        get() = _password!!

}