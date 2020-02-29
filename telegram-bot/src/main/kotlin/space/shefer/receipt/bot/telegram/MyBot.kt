package space.shefer.receipt.bot.telegram

import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.objects.Update
import space.shefer.receipt.bot.telegram.message_handlers.MessageHandler

class ReceiptTelegramBot(
        private val token: String
) : TelegramLongPollingBot() {

    override fun onUpdateReceived(update: Update) {
        MessageHandler.handle(this, update)
    }

    override fun getBotToken(): String = token

    override fun getBotUsername(): String = "Receipt Bot"

}

