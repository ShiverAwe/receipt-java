package space.shefer.receipt.bot.model

import javax.persistence.*

@Entity
@Table(name = "users")
class UserProfile {

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    var id: Long? = null

    @Column(name = "phone_number", unique = true, nullable = false)
    lateinit var phoneNumber: String

    @Column(name = "first_name", nullable = false)
    lateinit var firstName: String

    @Column(name = "last_name", nullable = false)
    lateinit var lastName: String

}
