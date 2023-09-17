package YourSSU_Backend.Assignment.user.model

import YourSSU_Backend.Assignment.article.model.Article
import YourSSU_Backend.Assignment.comment.model.Comment
import YourSSU_Backend.Assignment.global.BaseTime
import org.springframework.security.crypto.password.PasswordEncoder
import javax.persistence.*

@Entity
@Table(
    uniqueConstraints = [UniqueConstraint(name = "user_email", columnNames = ["email"])]
)
class User(userId: Long?, email: String, password: String?, username: String): BaseTime() {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    var userId: Long ? = null

    @Column(nullable = false, length = 30)
    var email: String = email

    @Column(nullable = false, length = 100)
    var password: String? = password

    @Column(nullable = false, length = 10)
    var username: String = username


    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val articleList: List<Article> = ArrayList<Article>()

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val commentList: List<Comment> = ArrayList<Comment>()

    fun passwordEncode(passwordEncoder: PasswordEncoder) {
        password = passwordEncoder.encode(password)
    }
}