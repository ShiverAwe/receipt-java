package space.shefer.receipt.bot.service

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Contact
import space.shefer.receipt.bot.model.UserProfile
import space.shefer.receipt.bot.repository.UserProfileRepository

@Service
class UserProfileService(
        private val userProfileRepository: UserProfileRepository
) {

    fun getOrCreate(contact: Contact): UserProfile {
        return userProfileRepository.findByPhoneNumber(contact.phoneNumber)
                ?: userProfileRepository.save(UserProfile().also {
                    it.firstName = contact.firstName
                    it.lastName = contact.lastName
                    it.phoneNumber = contact.phoneNumber
                })
    }

}
