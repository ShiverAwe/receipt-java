package space.shefer.receipt.bot.model

import javax.persistence.*

@Entity
@Table(name = "private_chats")
class PrivateChat {

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    var id: String? = null

    @Column(name = "chatId", nullable = false)
    lateinit var chatId: String

    @ManyToOne
    @JoinColumn(name = "user_profile_id")
    lateinit var userProfile: UserProfile


}
