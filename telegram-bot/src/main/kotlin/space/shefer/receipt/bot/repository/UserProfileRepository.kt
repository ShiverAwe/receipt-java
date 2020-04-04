package space.shefer.receipt.bot.repository

import org.springframework.data.jpa.repository.JpaRepository
import space.shefer.receipt.bot.model.UserProfile

interface UserProfileRepository: JpaRepository<UserProfile, String>{

    fun findByPhoneNumber(phoneNumber:String): UserProfile?

}
