package space.shefer.receipt.bot.telegram.message_handlers

import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import space.shefer.receipt.bot.service.PrivateChatService
import space.shefer.receipt.bot.service.UserProfileService

@Component
class AuthorizationMessageHandler(
        private val privateChatService: PrivateChatService
) : MessageHandler {

    override fun handle(bot: TelegramLongPollingBot, update: Update) {
        val contact = update.message?.contact ?: return
        val chatId = update.message.chatId.toString()
        val botId = bot.botToken.split(':')[0]
        privateChatService.auth(contact, chatId, botId)

        bot.execute(SendMessage(chatId, "Привет, ${contact.firstName}  ${contact.lastName}"))
    }

}
