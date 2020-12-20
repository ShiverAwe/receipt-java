package space.shefer.receipt.importers.email

import org.springframework.stereotype.Component
import space.shefer.receipt.importers.email.service.EmailServiceImpl
import javax.annotation.PostConstruct

@Component
class Mail(
    private val emailServiceImpl: EmailServiceImpl
) {

    @PostConstruct
    fun run() {
//        emailServiceImpl.sendSimpleMessage(
//                "quameu@gmail.com",
//                "Test",
//                "Thank you for your receipts!"
//        )
        emailServiceImpl.readMessages()
    }

}
