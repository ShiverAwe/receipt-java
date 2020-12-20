package space.shefer.receipt.importers.email.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.integration.mail.ImapMailReceiver
import org.springframework.integration.mail.MailReceiver
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component
import javax.mail.BodyPart
import javax.mail.Message
import javax.mail.Multipart
import javax.mail.Part

@Component
class EmailServiceImpl(
        @Value("\${spring.mail.imap.url}") private val imapUrl: String,
        @Value("\${spring.mail.pop3.url}") private val pop3Url: String
) {

    @Autowired
    private lateinit var emailSender: JavaMailSender

//    private var receiver: MailReceiver = Pop3MailReceiver(pop3Url)
    var receiver: MailReceiver = ImapMailReceiver(imapUrl)

    fun sendSimpleMessage(
            to: String,
            subject: String,
            text: String) {
        val message = SimpleMailMessage()
        message.setFrom("tmp1@shefer.space")
        message.setTo(to)
        message.setSubject(subject)
        message.setText(text)
        emailSender.send(message)
    }

    fun readMessages() {
        val messages = receiver.receive().mapNotNull { it as? Message }

        for (message in messages) {
            val attachments = getAttachments(message)
            print(attachments.map { it.first })
        }

    }

    private fun getAttachments(message: Message): List<Pair<String, ByteArray>> {
        val parts = getMessageParts(message)

        return parts
                .filter { bodyPart ->
                    bodyPart.disposition.toLowerCase() == Part.ATTACHMENT
                }
                .filter { bodyPart ->
                    !bodyPart.fileName.isBlank()
                }
                .map { bodyPart ->
                    val inputStream = bodyPart.inputStream
                    val data = inputStream.readBytes()
                    bodyPart.fileName to data
                }
    }

    private fun getMessageParts(message: Message): List<BodyPart> {
        val multipart = message.content as Multipart

        return (0 until multipart.count).map(multipart::getBodyPart)
    }
}
