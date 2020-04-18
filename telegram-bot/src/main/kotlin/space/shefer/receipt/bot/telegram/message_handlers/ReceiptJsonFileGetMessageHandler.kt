package space.shefer.receipt.bot.telegram.message_handlers

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.GetFile
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Document
import org.telegram.telegrambots.meta.api.objects.Update
import space.shefer.receipt.bot.model.PrivateChat
import space.shefer.receipt.bot.receipt.ReceiptWebClient
import space.shefer.receipt.bot.utils.getBotId
import java.nio.file.Files

@Component
class ReceiptJsonFileGetMessageHandler(
        val receiptWebClient: ReceiptWebClient
) : MessageHandler {
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun handle(bot: TelegramLongPollingBot, update: Update, privateChat: PrivateChat?) {
        val document: Document = update.message?.document ?: run {
            logger.info("No file present")
            return
        }

        if (!document.fileName.endsWith(".json")) {
            logger.info("File name was ${document.fileName}")
            return
        }

        val getFile = GetFile().also {
            it.fileId = document.fileId
        }

        val downloadFile = bot.downloadFile(bot.execute(getFile))

        val receiptJsonBody = String(Files.readAllBytes(downloadFile.toPath()))

        logger.trace(receiptJsonBody)

        val botId = bot.getBotId()
        val chatId = update.message!!.chatId!!
        val userId = privateChat?.userProfile?.phoneNumber ?: "$botId:$chatId"

        runCatching { receiptWebClient.sendReceiptJson(userId, receiptJsonBody) }
                .onSuccess { receiptId ->
                    bot.execute(SendMessage(chatId, getMessageSuccess(receiptId)).also {
                        it.replyToMessageId = update.message.messageId
                    })
                }
                .onFailure { e ->
                    logger.error("Could not send receipt", e)
                    bot.execute(SendMessage(chatId, getMessageError(e)).also {
                        it.replyToMessageId = update.message.messageId
                    })
                }
    }

    private fun getMessageSuccess(receiptId: String) =
            "Thanks for your receipt!\nhttps://receipt.shefer.space/receipt/${receiptId}"

    private fun getMessageError(e: Throwable) =
            "Receipt was not published due to error: ${e.message}"
}
