package YourSSU_Backend.Assignment.user.controller

import YourSSU_Backend.Assignment.user.dto.UserDeleteRequestDto
import global.config.logger
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import YourSSU_Backend.Assignment.user.dto.UserRequestDto
import YourSSU_Backend.Assignment.user.dto.UserResponseDto
import YourSSU_Backend.Assignment.user.model.User
import YourSSU_Backend.Assignment.user.service.UserService
import javax.validation.Valid

@RestController
class UserController (
    private var userService: UserService
    ) {
    /**
     * Email을 통한 회원가입
     * @param User
     * @throws Exception 중복회원 가입시 예외
     */
    @PostMapping("/signup/email")
    @ResponseStatus(HttpStatus.OK)
    fun signUp( @Valid @RequestBody userRequestDto: UserRequestDto): UserResponseDto{
        val userResponseDto: UserResponseDto = userService.signUp(userRequestDto)
        val log = logger()
        log.info("Create member by email: " + userRequestDto.email)
        return userResponseDto
    }

    @DeleteMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    fun withdraw(@Valid @RequestBody userDeleteRequestDto: UserDeleteRequestDto){
        userService.withdraw(userDeleteRequestDto)
        val log = logger()
        log.info("Delete member by email: " + userDeleteRequestDto.email)
    }

}