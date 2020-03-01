package space.shefer.receipt.bot

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.telegram.telegrambots.ApiContextInitializer
import org.telegram.telegrambots.meta.TelegramBotsApi
import space.shefer.receipt.bot.telegram.ReceiptTelegramBot

@SpringBootApplication
class ReceiptBotApplication {

    companion object {

        @Bean
        fun getTelegramBotsApi(receiptTelegramBot: ReceiptTelegramBot): TelegramBotsApi {
            return TelegramBotsApi().also {
                it.registerBot(receiptTelegramBot)
            }
        }

    }

}

fun main(args: Array<String>) {
    ApiContextInitializer.init()
    SpringApplication.run(ReceiptBotApplication::class.java, *args)
}
