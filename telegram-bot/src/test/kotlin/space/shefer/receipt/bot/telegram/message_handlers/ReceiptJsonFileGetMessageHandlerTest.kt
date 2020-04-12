package space.shefer.receipt.bot.telegram.message_handlers

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.GetFile
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import space.shefer.receipt.bot.receipt.ReceiptWebClient
import space.shefer.receipt.tests.util.ResourceUtil
import org.telegram.telegrambots.meta.api.objects.File as TelegramFile

class ReceiptJsonFileGetMessageHandlerTest {

    private val receiptWebClient: ReceiptWebClient = mock(ReceiptWebClient::class.java)
    private val handler = ReceiptJsonFileGetMessageHandler(receiptWebClient)
    private val bot: TelegramLongPollingBot = mock(TelegramLongPollingBot::class.java)

    @Test
    fun name() {
        val update = ObjectMapper().readValue(
                ResourceUtil.getResourceAsString("/bot/update/receipt_sent.update.json", javaClass),
                Update::class.java
        )

        val telegramFile = TelegramFile()

        doAnswer { telegramFile }.`when`(bot).execute(ArgumentMatchers.argThat<GetFile> {
            it.fileId == "fileId1"
        })

        doAnswer { "botId1:botPrivateKey1" }.`when`(bot).getBotToken()

        val receiptJsonFilePath = "/bot/update/receipt_sent.receipt.json"
        val receiptJson = ResourceUtil.getResourceAsString(receiptJsonFilePath, javaClass)
        val jsonFile = ResourceUtil.getResourceAsFile(receiptJsonFilePath, javaClass)

        doAnswer { jsonFile }.`when`(bot).downloadFile(telegramFile)

        handler.handle(bot, update, null)

        verify(receiptWebClient).sendReceiptJson("botId1:347937466", receiptJson)

        verify(bot).execute(argThat<SendMessage> {
            it.text.contains("Thanks for your receipt")
                    && it.text.contains("receipt.shefer.space/receipt")
        })
    }

}
