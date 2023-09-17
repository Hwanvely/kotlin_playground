package YourSSU_Backend.Assignment.article.service

import YourSSU_Backend.Assignment.article.dto.ArticleRequestDto
import YourSSU_Backend.Assignment.article.dto.ArticleResponseDto
import YourSSU_Backend.Assignment.article.model.Article
import YourSSU_Backend.Assignment.article.repository.ArticleRepository
import YourSSU_Backend.Assignment.global.exception.MissMatchArticleException
import YourSSU_Backend.Assignment.global.exception.NotFoundArticleException
import YourSSU_Backend.Assignment.global.exception.NotFoundMemberException
import org.springframework.stereotype.Service
import YourSSU_Backend.Assignment.user.model.User
import YourSSU_Backend.Assignment.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import javax.transaction.Transactional


@Service
class ArticleService(
    private var articleRepository : ArticleRepository,

    private var userRepository: UserRepository
) {
    /**
     *
     */
    fun createArticle( articleRequestDto: ArticleRequestDto) : ArticleResponseDto {
        var user: User = userRepository.findByEmail(articleRequestDto.email) ?: throw NotFoundMemberException("사용자를 찾을 수 없습니다")
        var newArticle: Article = articleRequestDto.toEntity(user)
        articleRepository.save(newArticle)
        return ArticleResponseDto.fromEntity(user,newArticle)
    }

    @Transactional
    fun updateArticle(articleId: Long?, articleRequestDto: ArticleRequestDto) : ArticleResponseDto{
        var user: User = checkWriter(articleId, articleRequestDto.email)
        var updateArticle: Article = articleRepository.findByIdOrNull(articleId!!) ?: throw NotFoundArticleException("게시글이 존재하지 않습니다")
        updateArticle.editArticle(articleRequestDto)

        return ArticleResponseDto.fromEntity(user,updateArticle)
    }

    @Transactional
    fun deleteArticle(articleId : Long?, email: String){
        checkWriter(articleId,email)
        articleRepository.findByIdOrNull(articleId!!) ?: throw NotFoundArticleException("게시글이 존재하지 않습니다")
        articleRepository.deleteById(articleId)
    }

    private fun checkWriter(articleId: Long?, email: String) :  User{

        var user: User = userRepository.findByEmail(email) ?: throw NotFoundMemberException("사용자를 찾을 수 없습니다")
        var writerId : Long = articleRepository.findByIdOrNull(articleId!!)?.user?.userId ?: throw NotFoundMemberException("작성자가 존재하지 않습니다.")
        if(writerId != user.userId)
        {
               throw MissMatchArticleException("작성자가 일치하지 않습니다")
        }
        return user
    }


}

