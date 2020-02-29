package space.shefer.receipt.bot.telegram

import org.telegram.telegrambots.ApiContextInitializer
import org.telegram.telegrambots.meta.TelegramBotsApi

fun main(args: Array<String>) {
    val token = args[0]

    ApiContextInitializer.init()

    val botsApi = TelegramBotsApi()

    botsApi.registerBot(ReceiptTelegramBot(token))

}
