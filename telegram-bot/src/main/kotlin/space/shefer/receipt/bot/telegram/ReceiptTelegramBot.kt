package space.shefer.receipt.bot.telegram

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.DefaultBotOptions
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.objects.Update
import space.shefer.receipt.bot.service.PrivateChatService
import space.shefer.receipt.bot.telegram.message_handlers.MessageHandler
import space.shefer.receipt.bot.utils.getBotId

@Component
class ReceiptTelegramBot(
        private val messageHandlers: List<MessageHandler>,
        private val proxyOptions: TelegramHttpProxyOptions,
        private val privateChatService: PrivateChatService
) : TelegramLongPollingBot(DefaultBotOptions().also {
    if (proxyOptions.enabled) {
        it.proxyHost = proxyOptions.host!!
        it.proxyPort = proxyOptions.port!!
        it.proxyType = DefaultBotOptions.ProxyType.HTTP
    }
}) {

    @Value("\${bot.token}")
    lateinit var token: String

    override fun onUpdateReceived(update: Update) {
        val privateChat = if (update.message != null) {
            privateChatService.getByBotIdChatId(
                    this.getBotId(),
                    update.message.chatId.toString()
            )
        } else {
            null
        }

        messageHandlers.forEach {
            runCatching {
                it.handle(this, update, privateChat)
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    override fun getBotToken(): String = token

    override fun getBotUsername(): String = "Receipt Bot"

}
