package space.shefer.receipt.fnssdk.webclient

import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestTemplate
import space.shefer.receipt.fnssdk.primainform.MerchantSuggestion
import java.net.URI

class Merchant {

    fun getMerchantFromPrimaInformOrNone(inn: String): MerchantSuggestion? {

        val responseEntity = RestTemplate().exchange(
                URI.create("https://www.prima-inform.ru/utils/search?query=" + inn),
                HttpMethod.GET,
                HttpEntity<String>(""),
                MerchantSuggestion::class.java
        )
        responseEntity.body?.suggestions?.
        filter {  it.data.inn == inn }?.
        map{suggestion-> mapOf("inn" to suggestion.data.inn, "address" to suggestion.data.address,"name" to suggestion.value)}

        return responseEntity.body
    }
}
