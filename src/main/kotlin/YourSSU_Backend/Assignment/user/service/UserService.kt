package YourSSU_Backend.Assignment.user.service


import YourSSU_Backend.Assignment.global.exception.AlreadyExistException
import YourSSU_Backend.Assignment.global.exception.NotFoundMemberException
import YourSSU_Backend.Assignment.user.dto.UserDeleteRequestDto
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import YourSSU_Backend.Assignment.user.dto.UserRequestDto
import YourSSU_Backend.Assignment.user.dto.UserResponseDto
import YourSSU_Backend.Assignment.user.model.User
import YourSSU_Backend.Assignment.user.repository.UserRepository
import javax.transaction.Transactional

@Transactional
@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder

){
    /**
     * Email을 통한 회원가입
     * @param userRequestDto
     * @return user 회원가입한 user
     */
     fun signUp (userRequestDto: UserRequestDto): UserResponseDto {
        validateDuplicateMember(userRequestDto)
        var user = userRequestDto.toEntity()
        user.passwordEncode(passwordEncoder)
        val newUser = userRepository.save(user)
        return UserResponseDto.fromEntity(newUser)
    }

    fun withdraw(userDeleteRequestDto: UserDeleteRequestDto) {
        var user : User = userRepository.findByEmail(userDeleteRequestDto.email) ?: throw NotFoundMemberException("사용자를 찾을 수 없습니다.")
        userRepository.deleteById(user.userId!!)
    }

    /**
     * Email 중복 확인
     * @param userRequestDto
     */
    private fun validateDuplicateMember(userRequestDto: UserRequestDto) {

        if (userRepository.existsByEmail(userRequestDto.email)) {
            throw AlreadyExistException("이미 등록된 이메일입니다.")
        }
    }
}
