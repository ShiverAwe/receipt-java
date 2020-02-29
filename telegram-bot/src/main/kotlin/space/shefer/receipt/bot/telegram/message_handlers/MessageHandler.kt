package space.shefer.receipt.bot.telegram.message_handlers

import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.objects.Update

interface MessageHandler {

    fun handle(bot: TelegramLongPollingBot, update: Update)

}
