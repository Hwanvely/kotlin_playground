package YourSSU_Backend.Assignment.comment.dto


import YourSSU_Backend.Assignment.comment.model.Comment
import YourSSU_Backend.Assignment.user.model.User
import com.fasterxml.jackson.annotation.JsonProperty

class CommentResponseDto(


    @JsonProperty("commentId")
    private val _commentId: Long ,


    @JsonProperty("email")
    private val _email: String ,


    @JsonProperty("content")
    private val _content: String

) {
    val commentId: Long
        get() = _commentId!!

    val email: String
        get() = _email!!

    val content: String
        get() = _content!!


    companion object {
        fun fromEntity(user: User, comment: Comment): CommentResponseDto {
            return CommentResponseDto(user.userId!!, user.email, comment.content)
        }
    }
}