package YourSSU_Backend.Assignment.user.service

import YourSSU_Backend.Assignment.global.exception.AlreadyExistException
import YourSSU_Backend.Assignment.user.dto.UserRequestDto
import YourSSU_Backend.Assignment.user.model.User
import YourSSU_Backend.Assignment.user.repository.UserRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyString
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootTest
internal class UserServiceTest {

    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var passwordEncoder: PasswordEncoder

    @InjectMocks
    private lateinit var userService: UserService

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun 회원가입중복() {
        // given
        val userDto = UserRequestDto("email@urssu.com", "password", "jeonghwan")
        given(userRepository.existsByEmail(userDto.email)).willReturn(true)

        // when - then
        assertThrows<AlreadyExistException> {
            userService.signUp(userDto)
        }
    }

    @Test
    fun 회원가입실패() {
        // given
        val userDto = UserRequestDto("email@urssu.com", "password", "username")
        given(userRepository.existsByEmail(userDto.email)).willReturn(false)
        given(passwordEncoder.encode(anyString())).willReturn("encodedPassword")
        given(userRepository.save(any(User::class.java))).willReturn(User(null, userDto.email, userDto.password, userDto.username))

        // when
        val result = userService.signUp(userDto)

        // then
        assertEquals("email@urssu.com", result.email)
    }

}
