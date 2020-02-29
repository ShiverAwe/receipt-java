package space.shefer.receipt.bot.telegram.message_handlers

import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.objects.Update

interface MessageHandler {

    fun handle(bot: TelegramLongPollingBot, update: Update)

    companion object {
        private val instances: List<MessageHandler> = listOf(
                ReceiptJsonFileGetMessageHandler()
        )

        fun handle(bot: TelegramLongPollingBot, update: Update) {
            instances.forEach {
                runCatching {
                    it.handle(bot, update)
                }
            }
        }
    }

}
