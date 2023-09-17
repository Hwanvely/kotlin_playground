package YourSSU_Backend.Assignment.article.dto

import YourSSU_Backend.Assignment.article.model.Article
import com.fasterxml.jackson.annotation.JsonProperty
import YourSSU_Backend.Assignment.user.model.User
import javax.validation.constraints.NotBlank

data class ArticleRequestDto(


    @field:NotBlank
    @JsonProperty("email")
    private val _email: String ,

    @field:NotBlank
    @JsonProperty("password")
    private val _password: String ,


    @field:NotBlank
    @JsonProperty("content")
    private val _content: String ,

    @field:NotBlank
    @JsonProperty("title")
    private val _title: String

) {
    val email: String
        get() = _email!!

    val password: String
        get() = _password!!

    val content: String
        get() = _content!!
    val title: String
        get() = _title!!

    fun toEntity(user: User): Article {
        return Article(null, this.content, this.title, user)
    }
}


