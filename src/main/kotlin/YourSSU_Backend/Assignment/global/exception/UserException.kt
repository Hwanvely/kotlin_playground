package YourSSU_Backend.Assignment.global.exception

import org.springframework.http.HttpStatus

//중복으로 회원가입 시
class AlreadyExistException(override val customMessage: String) : RuntimeException(customMessage), CustomException {
    override val status: HttpStatus = HttpStatus.CONFLICT
}

//회원정보가 없을 시
class NotFoundMemberException(override val customMessage: String) : RuntimeException(customMessage), CustomException {
    override val status: HttpStatus = HttpStatus.NOT_FOUND
}
