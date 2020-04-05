package space.shefer.receipt.bot.telegram.message_handlers

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.send.SendSticker
import org.telegram.telegrambots.meta.api.objects.Contact
import org.telegram.telegrambots.meta.api.objects.Update
import space.shefer.receipt.bot.model.PrivateChat
import space.shefer.receipt.bot.service.PrivateChatService
import space.shefer.receipt.bot.utils.getBotId

@Component
class AuthorizationMessageHandler(
        private val privateChatService: PrivateChatService
) : MessageHandler {

    @Value("\${telegram.greetings.sticker:#{null}}")
    var greetingSticker: String? = null

    override fun handle(bot: TelegramLongPollingBot, update: Update, privateChat: PrivateChat?) {
        val contact = update.message?.contact ?: return
        val chatId = update.message.chatId.toString()
        val botId = bot.getBotId()
        privateChatService.auth(contact, chatId, botId)

        this.greeting(bot, chatId, contact)
    }

    private fun greeting(bot: TelegramLongPollingBot, chatId: String, contact: Contact) {
        bot.execute(SendMessage(chatId, "Привет, ${contact.firstName} ${contact.lastName.orEmpty()}"))

        if (greetingSticker != null) {
            val stickerMessage = SendSticker()
            stickerMessage.chatId = chatId
            stickerMessage.setSticker(greetingSticker)
            bot.execute(stickerMessage)
        }
    }

}
