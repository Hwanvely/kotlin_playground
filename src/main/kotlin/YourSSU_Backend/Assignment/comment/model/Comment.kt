package YourSSU_Backend.Assignment.comment.model

import YourSSU_Backend.Assignment.article.model.Article
import YourSSU_Backend.Assignment.comment.dto.CommentRequestDto
import YourSSU_Backend.Assignment.global.BaseTime
import YourSSU_Backend.Assignment.user.model.User
import javax.persistence.*

@Entity
class Comment(id: Long?, content: String, user: User, article: Article): BaseTime(){
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "comment_id")
        var commentId: Long ? = null

        @Column(nullable = false, length = 100)
        var content: String = content

        @ManyToOne
        @JoinColumn(name = "user_id")
        var user : User =  user

        @ManyToOne
        @JoinColumn(name = "article_id")
        var article : Article =  article

        fun editComment(commentRequestDto: CommentRequestDto) {
                this.content = commentRequestDto.content
        }
}
