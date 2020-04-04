package space.shefer.receipt.bot.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import space.shefer.receipt.bot.model.UserProfile

interface UserProfileRepository: JpaRepository<UserProfile, String>{

    @Query(
            """
                SELECT u
                FROM UserProfile u
                WHERE phoneNumber = :phoneNumber
            """
    )
    fun findByPhoneNumber(phoneNumber:String): UserProfile?

}
