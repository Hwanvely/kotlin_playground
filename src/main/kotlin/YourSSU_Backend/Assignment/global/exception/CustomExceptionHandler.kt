import YourSSU_Backend.Assignment.global.exception.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class CustomExceptionHandler {

    @ExceptionHandler(value = [MissMatchArticleException::class])
    fun handleMissMatchException(exception: MissMatchArticleException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        return createErrorResponse(exception, request)
    }

    @ExceptionHandler(value = [NotFoundArticleException::class])
    fun handleNotFoundArticleException(exception: NotFoundArticleException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        return createErrorResponse(exception, request)
    }

    @ExceptionHandler(value = [NotFoundMemberException::class])
    fun handleNotFoundMemberException(exception: NotFoundMemberException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        return createErrorResponse(exception, request)
    }
    @ExceptionHandler(value = [AlreadyExistException::class])
    fun handleAlreadyException(exception: AlreadyExistException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        return createErrorResponse(exception, request)
    }

    @ExceptionHandler(value = [NotFoundCommentException::class])
    fun handleNotFoundCommentException(exception: NotFoundCommentException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        return createErrorResponse(exception, request)
    }

    @ExceptionHandler(value = [MissMatchCommentException::class])
    fun handleMissMatchCommentException(exception: MissMatchCommentException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        return createErrorResponse(exception, request)
    }

    private fun createErrorResponse(exception: CustomException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            time = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
            status = exception.status.toString(),
            message = exception.customMessage,
            requestURI = request.requestURI
        )
        return ResponseEntity(errorResponse, exception.status)
    }
}
