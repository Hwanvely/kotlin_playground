package YourSSU_Backend.Assignment.global

import org.hibernate.annotations.CreationTimestamp
import org.springframework.data.jpa.domain.AbstractAuditable_.createdDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass


@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseTime {
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    val createdDate: LocalDateTime? = null

    @CreationTimestamp
    @Column(nullable = false)
    val updatedDate: LocalDateTime? = null

    fun getCDate(): LocalDateTime? {
        return createdDate
    }

    fun getMDate(): LocalDateTime? {
        return updatedDate
    }
}