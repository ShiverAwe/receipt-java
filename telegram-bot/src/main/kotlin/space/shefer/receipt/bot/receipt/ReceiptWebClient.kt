package space.shefer.receipt.bot.receipt

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import space.shefer.receipt.rest.dto.TgbotCreateBody
import java.net.URI

@Component
class ReceiptWebClient {
    @Value("\${receipt.api.url}")
    lateinit var receiptApiUrl: String

    private val restTemplate = RestTemplate()

    fun sendReceiptJson(userId: String, receiptJson: String): String {
        val body = TgbotCreateBody().also {
            it.userId = userId
            it.receiptJson = receiptJson
        }

        val responseEntity = restTemplate.exchange(
                URI.create("$receiptApiUrl/tgbot/create"),
                HttpMethod.POST,
                HttpEntity(body),
                String::class.java
        )

        return responseEntity.body!!
    }

}
