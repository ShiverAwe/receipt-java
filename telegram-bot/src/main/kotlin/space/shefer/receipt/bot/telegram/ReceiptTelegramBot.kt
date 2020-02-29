package space.shefer.receipt.bot.telegram

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.objects.Update
import space.shefer.receipt.bot.telegram.message_handlers.MessageHandler

@Component
class ReceiptTelegramBot(
        private val messageHandlers: List<MessageHandler>
) : TelegramLongPollingBot() {

    @Value("\${bot.token}")
    lateinit var token: String

    override fun onUpdateReceived(update: Update) {
        messageHandlers.forEach {
            runCatching {
                it.handle(this, update)
            }
        }
    }

    override fun getBotToken(): String = token

    override fun getBotUsername(): String = "Receipt Bot"

}
