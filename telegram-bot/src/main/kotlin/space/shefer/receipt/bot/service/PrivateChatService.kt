package space.shefer.receipt.bot.service

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Contact
import space.shefer.receipt.bot.model.PrivateChat
import space.shefer.receipt.bot.repository.PrivateChatRepository

@Service
class PrivateChatService(
        private val privateChatRepository: PrivateChatRepository,
        private val userProfileService: UserProfileService
) {

    fun auth(contact: Contact, chatId: String, botId: String): PrivateChat {
        val userProfile = userProfileService.getOrCreate(contact)

        return privateChatRepository.findByChatIdAndBotId(chatId, botId)
                ?: privateChatRepository.save(PrivateChat().also {
                    it.chatId = chatId
                    it.botId = botId
                    it.userProfile = userProfile
                })
    }

    fun getByBotIdChatId(botId: String, chatId: String): PrivateChat? {
        return privateChatRepository.findByChatIdAndBotId(chatId, botId)
    }

}
