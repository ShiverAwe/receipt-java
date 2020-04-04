package space.shefer.receipt.bot.utils

import org.telegram.telegrambots.bots.TelegramLongPollingBot

fun TelegramLongPollingBot.getBotId() = botToken.split(':')[0]

