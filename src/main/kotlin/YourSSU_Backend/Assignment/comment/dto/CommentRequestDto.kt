package YourSSU_Backend.Assignment.comment.dto

import YourSSU_Backend.Assignment.article.model.Article
import YourSSU_Backend.Assignment.comment.model.Comment
import YourSSU_Backend.Assignment.user.model.User
import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotBlank

class CommentRequestDto (


    @field:NotBlank
    @JsonProperty("email")
    private val _email: String ,

    @field:NotBlank
    @JsonProperty("password")
    private val _password: String ,


    @field:NotBlank
    @JsonProperty("content")
    private val _content: String

) {
    val email: String
        get() = _email!!

    val password: String
        get() = _password!!

    val content: String
        get() = _content!!

    fun toEntity(user: User, article: Article): Comment {
        return Comment(null, content, user, article)
    }
}