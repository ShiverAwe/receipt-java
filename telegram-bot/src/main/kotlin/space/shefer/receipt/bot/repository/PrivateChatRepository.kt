package space.shefer.receipt.bot.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import space.shefer.receipt.bot.model.PrivateChat
import space.shefer.receipt.bot.model.UserProfile

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
    fun findByChatId(chatId:String): PrivateChat?
    fun findByUserProfile(userProfile: UserProfile): List<PrivateChat>

}
