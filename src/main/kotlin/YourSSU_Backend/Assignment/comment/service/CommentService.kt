package YourSSU_Backend.Assignment.comment.service

import YourSSU_Backend.Assignment.article.model.Article
import YourSSU_Backend.Assignment.article.repository.ArticleRepository
import YourSSU_Backend.Assignment.comment.dto.CommentRequestDto
import YourSSU_Backend.Assignment.comment.dto.CommentResponseDto
import YourSSU_Backend.Assignment.comment.model.Comment
import YourSSU_Backend.Assignment.comment.repository.CommentRepository
import YourSSU_Backend.Assignment.global.exception.*
import YourSSU_Backend.Assignment.user.model.User
import YourSSU_Backend.Assignment.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional


@Service
class CommentService(
    private var articleRepository : ArticleRepository,

    private var userRepository: UserRepository,

    private var commentRepository: CommentRepository
) {
    /**
     *
     */
    fun createComment( articleId: Long?, commentRequestDto: CommentRequestDto) : CommentResponseDto {
        var user: User = userRepository.findByEmail(commentRequestDto.email) ?: throw NotFoundMemberException("사용자를 찾을 수 없습니다")
        var article: Optional<Article> = articleRepository.findById(articleId!!)
        val findArticle: Article = article.orElseThrow { NotFoundArticleException("게시글이 존재하지않습니다")}
        var newComment: Comment = commentRequestDto.toEntity(user, findArticle)
        commentRepository.save(newComment)
        return CommentResponseDto.fromEntity(user, newComment)
    }

    @Transactional
    fun updateComment(commentId: Long?, articleId: Long?, commentRequestDto: CommentRequestDto) : CommentResponseDto {
        var user: User = checkCommentWriter(commentId, commentRequestDto.email)
        var article: Article = articleRepository.findByIdOrNull(articleId!!) ?: throw NotFoundArticleException("게시글이 존재하지 않습니다")
        var updateComment: Comment = commentRepository.findByIdOrNull(commentId!!) ?: throw NotFoundCommentException("댓글이 존재하지 않습니다")
        updateComment.editComment(commentRequestDto)

        return CommentResponseDto.fromEntity(user,updateComment)
    }

    @Transactional
    fun deleteComment(commentId: Long?, articleId : Long?, email: String){
        checkCommentWriter(commentId,email)
        articleRepository.findByIdOrNull(articleId!!) ?: throw NotFoundArticleException("게시글이 존재하지 않습니다")
        commentRepository.findByIdOrNull(commentId!!) ?: throw NotFoundCommentException("댓글이 존재하지 않습니다")
        commentRepository.deleteById(commentId)
    }

    private fun checkCommentWriter(commentId: Long?, email: String) : User {

        var user: User = userRepository.findByEmail(email) ?: throw NotFoundMemberException("사용자를 찾을 수 없습니다")
        var writerId : Long = commentRepository.findByIdOrNull(commentId!!)?.user?.userId ?: throw NotFoundMemberException("작성자가 존재하지 않습니다.")
        if(writerId != user.userId)
        {
            throw MissMatchCommentException("작성자가 일치하지 않습니다")
        }
        return user
    }


}
