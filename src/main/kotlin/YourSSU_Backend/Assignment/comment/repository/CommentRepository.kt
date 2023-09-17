package YourSSU_Backend.Assignment.comment.repository

import YourSSU_Backend.Assignment.comment.model.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository: JpaRepository<Comment, Long> {
}