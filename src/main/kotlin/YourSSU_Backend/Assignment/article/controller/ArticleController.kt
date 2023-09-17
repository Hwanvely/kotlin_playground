package YourSSU_Backend.Assignment.article.controller

import YourSSU_Backend.Assignment.article.dto.ArticleDeleteRequestDto
import YourSSU_Backend.Assignment.article.dto.ArticleRequestDto
import YourSSU_Backend.Assignment.article.dto.ArticleResponseDto
import YourSSU_Backend.Assignment.article.service.ArticleService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class ArticleController(
    private var articleService: ArticleService
) {
    @PostMapping("/articles/new")
    @ResponseStatus(HttpStatus.OK)
    fun createArticle(@Valid @RequestBody articleRequestDto: ArticleRequestDto): ArticleResponseDto {
        return articleService.createArticle(articleRequestDto)
    }

    @PatchMapping("/articles/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateArticle(
        @Valid @PathVariable("id") id: Long,
        @RequestBody articleRequestDto: ArticleRequestDto
    ): ArticleResponseDto {
        return articleService.updateArticle(id, articleRequestDto)
    }

    @DeleteMapping("/articles/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteArticle(@Valid @PathVariable("id") id : Long,
                      @RequestBody articleDeleteRequestDto: ArticleDeleteRequestDto
                     ): HttpStatus {
        articleService.deleteArticle(id, articleDeleteRequestDto.email)
        return HttpStatus.OK
    }

}