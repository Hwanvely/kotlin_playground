package YourSSU_Backend.Assignment.user.repository

import org.springframework.data.jpa.repository.JpaRepository
import YourSSU_Backend.Assignment.user.model.User

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(Email : String) : User?

    fun existsByEmail(Email: String): Boolean
}