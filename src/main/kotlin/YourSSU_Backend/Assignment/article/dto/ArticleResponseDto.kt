package YourSSU_Backend.Assignment.article.dto

import YourSSU_Backend.Assignment.article.model.Article
import YourSSU_Backend.Assignment.user.model.User
import com.fasterxml.jackson.annotation.JsonProperty


class ArticleResponseDto (


    @JsonProperty("articleId")
    private val _articleId: Long ,


    @JsonProperty("email")
    private val _email: String ,


    @JsonProperty("content")
    private val _content: String ,


    @JsonProperty("title")
    private val _title: String

) {
    val articleId: Long
        get() = _articleId!!

    val email: String
        get() = _email!!

    val content: String
        get() = _content!!

    val title: String
        get() = _title!!

    companion object {
        fun fromEntity(user: User, article: Article): ArticleResponseDto {
            return ArticleResponseDto(user.userId!!, user.email, article.content, article.title)
        }
    }
}
