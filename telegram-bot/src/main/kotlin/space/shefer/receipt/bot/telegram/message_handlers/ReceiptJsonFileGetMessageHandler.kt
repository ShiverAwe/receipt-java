package space.shefer.receipt.bot.telegram.message_handlers

import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.GetFile
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Document
import org.telegram.telegrambots.meta.api.objects.Update
import java.nio.file.Files

class ReceiptJsonFileGetMessageHandler : MessageHandler {
    override fun handle(bot: TelegramLongPollingBot, update: Update) {
        val document: Document = update.message?.document ?: run {
            println("No file present")
            return
        }

        if (!document.fileName.endsWith(".json")) {
            println("File name was ${document.fileName}")
            return
        }

        val getFile = GetFile().also {
            it.fileId = document.fileId
        }

        val filePath = bot.execute(getFile).filePath

        val downloadFile = bot.downloadFile(filePath)

        val string = String(Files.readAllBytes(downloadFile.toPath()))

        println(string)

        val chatId = update.message!!.chatId!!
        val sendMessage = SendMessage(chatId, "Thanks for your receipt!")

        bot.execute(sendMessage)
    }
}
