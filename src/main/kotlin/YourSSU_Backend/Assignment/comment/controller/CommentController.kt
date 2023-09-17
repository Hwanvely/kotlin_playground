package YourSSU_Backend.Assignment.comment.controller

import YourSSU_Backend.Assignment.article.dto.ArticleDeleteRequestDto
import YourSSU_Backend.Assignment.article.dto.ArticleRequestDto
import YourSSU_Backend.Assignment.article.dto.ArticleResponseDto
import YourSSU_Backend.Assignment.article.service.ArticleService
import YourSSU_Backend.Assignment.comment.dto.CommentDeleteRequestDto
import YourSSU_Backend.Assignment.comment.dto.CommentRequestDto
import YourSSU_Backend.Assignment.comment.dto.CommentResponseDto
import YourSSU_Backend.Assignment.comment.service.CommentService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class CommentController(
    private var commentService: CommentService
    ) {
        @PostMapping("/comments/{id}")
        @ResponseStatus(HttpStatus.OK)
        fun createArticle(@Valid @PathVariable("id") articleId: Long,
                          @RequestBody commentRequestDto: CommentRequestDto): CommentResponseDto {
            return commentService.createComment(articleId, commentRequestDto)
        }

        @PatchMapping("/comments/{articleId}/{commentId}")
        @ResponseStatus(HttpStatus.OK)
        fun updateArticle(
            @Valid @PathVariable("articleId") articleId: Long, @PathVariable("commentId") commentId: Long,
            @RequestBody commentRequestDto: CommentRequestDto
        ): CommentResponseDto {
            return commentService.updateComment(commentId, articleId, commentRequestDto)
        }

        @DeleteMapping("/comments/{articleId}/{commentId}")
        @ResponseStatus(HttpStatus.OK)
        fun deleteArticle(@Valid @PathVariable("articleId") articleId: Long, @PathVariable("commentId") commentId: Long,
                          @RequestBody commentDeleteRequestDto: CommentDeleteRequestDto
        ): HttpStatus {
            commentService.deleteComment(commentId, articleId, commentDeleteRequestDto.email)
            return HttpStatus.OK
        }

    }
