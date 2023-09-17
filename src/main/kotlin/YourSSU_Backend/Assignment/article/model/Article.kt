package YourSSU_Backend.Assignment.article.model

import YourSSU_Backend.Assignment.article.dto.ArticleRequestDto
import YourSSU_Backend.Assignment.comment.model.Comment
import YourSSU_Backend.Assignment.global.BaseTime
import YourSSU_Backend.Assignment.user.model.User
import javax.persistence.*

@Entity
class Article(articleId: Long?, content: String, title: String, user: User) : BaseTime(){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    var articleId: Long ? = null

    @Column(nullable = false, length = 100)
    var content: String = content

    @Column(nullable = false, length = 100)
    var title: String = title

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user : User =  user

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val commentList: List<Comment> = ArrayList<Comment>()

    fun editArticle(articleRequestDto: ArticleRequestDto) {
        this.content = articleRequestDto.content
        this.title = articleRequestDto.title
    }
}
