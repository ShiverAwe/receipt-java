package space.shefer.receipt.bot.telegram.message_handlers

import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class AuthorizationMessageHandler : MessageHandler {
    override fun handle(bot: TelegramLongPollingBot, update: Update) {
        val contact = update.message?.contact ?: return



        bot.execute(SendMessage(update.message.chatId, "Привет, ${contact.firstName}  ${contact.lastName}"))

    }
}
