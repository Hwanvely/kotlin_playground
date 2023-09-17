package YourSSU_Backend.Assignment.article.repository

import YourSSU_Backend.Assignment.article.model.Article
import org.springframework.data.repository.CrudRepository
import java.util.*

interface ArticleRepository: CrudRepository<Article, Long> {

}