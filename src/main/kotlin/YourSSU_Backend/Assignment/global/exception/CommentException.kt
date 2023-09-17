package YourSSU_Backend.Assignment.global.exception

import org.springframework.http.HttpStatus

//댓글이 존재하지 않을
class NotFoundCommentException(override val customMessage: String) : RuntimeException(customMessage), CustomException {
    override val status: HttpStatus = HttpStatus.NOT_FOUND
}

//댓글 작성자가 일치하지 않을 때
class MissMatchCommentException(override val customMessage: String) : RuntimeException(customMessage), CustomException {
    override val status: HttpStatus = HttpStatus.FORBIDDEN
}