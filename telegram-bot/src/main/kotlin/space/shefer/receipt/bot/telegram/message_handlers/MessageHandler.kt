package space.shefer.receipt.bot.telegram.message_handlers

import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.objects.Update

interface MessageHandler {

    /**
     * Any exception may be thrown from this method.
     * Thrown exception is catched and ignored by call site.
     */
    fun handle(bot: TelegramLongPollingBot, update: Update)

}
