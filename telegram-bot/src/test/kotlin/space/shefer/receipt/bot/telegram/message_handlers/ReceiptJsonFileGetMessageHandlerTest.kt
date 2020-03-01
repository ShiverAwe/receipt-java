package space.shefer.receipt.bot.telegram.message_handlers

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Ignore
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import space.shefer.receipt.tests.util.ResourceUtil

@Ignore
class ReceiptJsonFileGetMessageHandlerTest {

    private val handler = ReceiptJsonFileGetMessageHandler()
    private val bot: TelegramLongPollingBot = Mockito.mock(TelegramLongPollingBot::class.java)

    @Test
    fun name() {
        val update = ObjectMapper().readValue(
                ResourceUtil.getResourceAsString("/bot/update/receipt_sent.update.json", javaClass),
                Update::class.java
        )

        doAnswer {
            TODO()
        }.`when`(bot)
                .execute(ArgumentMatchers.any<SendMessage>())

        handler.handle(bot, update)

        verify(bot).execute(argThat<SendMessage> { it.text != null })
    }
}
