package space.shefer.receipt.bot.model

import javax.persistence.*

@Entity
@Table(name = "private_chats")
class PrivateChat {

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    var id: Long? = null

    @Column(name = "chat_id", nullable = false)
    lateinit var chatId: String

    @Column(name = "bot_id", nullable = false)
    lateinit var botId: String

    @ManyToOne
    @JoinColumn(name = "user_profile_id")
    lateinit var userProfile: UserProfile

}
