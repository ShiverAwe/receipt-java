package space.shefer.receipt.bot.telegram.message_handlers

import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow
import space.shefer.receipt.bot.model.PrivateChat

@Component
class StartMessageHandler : MessageHandler {
    private val replyKeyboardMarkup: ReplyKeyboardMarkup = ReplyKeyboardMarkup(listOf(
            KeyboardRow().also { row ->
                row.add(KeyboardButton("Отправить номер телефона").also { button ->
                    button.requestContact = true
                })
            }
    ))

    override fun handle(bot: TelegramLongPollingBot, update: Update, privateChat: PrivateChat?) {
        if (update.message?.text != "/start") {
            return
        }

        val chatId = update.message?.chatId

        bot.execute(SendMessage(chatId, "Привет! Пожалуйста, отправьте номер телефона для авторизации.")
                .setReplyMarkup(replyKeyboardMarkup))

    }
}
