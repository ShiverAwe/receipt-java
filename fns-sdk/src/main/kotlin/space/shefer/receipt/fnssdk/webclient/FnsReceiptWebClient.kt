package space.shefer.receipt.fnssdk.webclient

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.net.URI
import java.util.*

@Component
class FnsReceiptWebClient {

    @Value("\${fns.login}")
    lateinit var login: String

    @Value("\${fns.password}")
    lateinit var password: String

    operator fun get(fn: String, fd: String, fp: String): String? {
        val uri = urlGet(fn, fd, fp)
        val headers = HttpHeaders()
        headers.add("device-id", "")
        headers.add("device-os", "")
        headers.add("Authorization", getAuthHeader(login, password))
        val requestEntity = HttpEntity<String>(headers)
        val restTemplate = RestTemplate()
        val responseEntity = restTemplate.exchange(
                URI.create(uri), HttpMethod.GET, requestEntity, String::class.java)
        val statusCode = responseEntity.statusCode
        return responseEntity.body
    }

    private fun getAuthHeader(login: String, password: String): String {
        val plainCredentials = "$login:$password"
        val plainCredentialsBytes = plainCredentials.toByteArray()
        val base64CredentialsBytes = Base64.getEncoder().encode(plainCredentialsBytes)
        val base64Credentials = String(base64CredentialsBytes)
        return "Basic $base64Credentials"
    }

    companion object {
        private const val HOST = "https://proverkacheka.nalog.ru:9999"
        private fun urlGet(fn: String, fd: String, fp: String): String {
            return HOST + "/v1/inns/*/kkts/*" +
                    "/fss/" + fn +
                    "/tickets/" + fd +
                    "?fiscalSign=" + fp +
                    "&sendToEmail=no"
        }
    }
}
