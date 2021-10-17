package space.shefer.receipt.merchants.webclient

import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import space.shefer.receipt.merchants.primainform.MerchantSuggestions
import java.net.URI

@Component
class PrimaInformWebClient {

    fun getSuggestions(inn: String): MerchantSuggestions? {

        val responseEntity = RestTemplate().exchange(
                URI.create("https://www.prima-inform.ru/utils/search?query=" + inn),
                HttpMethod.GET,
                HttpEntity.EMPTY,
                MerchantSuggestions::class.java
        )

        return responseEntity.body
    }

}
