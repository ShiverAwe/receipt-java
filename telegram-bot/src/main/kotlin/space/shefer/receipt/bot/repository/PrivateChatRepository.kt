package space.shefer.receipt.bot.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import space.shefer.receipt.bot.model.PrivateChat

interface PrivateChatRepository: JpaRepository<PrivateChat, String>{

    @Query(
            """
                SELECT p
                FROM PrivateChat p
                WHERE chatId = :chatId
                AND botId = :botId
            """
    )
    fun findByChatIdAndBotId(chatId:String, botId:String): PrivateChat?

}
