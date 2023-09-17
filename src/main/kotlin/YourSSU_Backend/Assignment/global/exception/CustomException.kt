package YourSSU_Backend.Assignment.global.exception

import org.springframework.http.HttpStatus

interface CustomException {

    val status: HttpStatus
    val customMessage: String

}