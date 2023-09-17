package YourSSU_Backend.Assignment.global.exception

import org.springframework.http.HttpStatus

//게시물 작성자가 일치하지 않을 때
class MissMatchArticleException(override val customMessage: String) : RuntimeException(customMessage), CustomException {
    override val status: HttpStatus = HttpStatus.FORBIDDEN
}

//게시글이 존재하지 않을때
class NotFoundArticleException(override val customMessage: String) : RuntimeException(customMessage), CustomException {
    override val status: HttpStatus = HttpStatus.NOT_FOUND
}

