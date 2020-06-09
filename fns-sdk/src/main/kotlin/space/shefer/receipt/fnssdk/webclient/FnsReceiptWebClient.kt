package space.shefer.receipt.fnssdk.webclient

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.RestTemplate
import space.shefer.receipt.fnssdk.excepion.AuthorizationFailedException
import java.net.URI
import java.util.*

@Component
class FnsReceiptWebClient {

    @Value("\${fns.login}")
    lateinit var login: String

    @Value("\${fns.password}")
    lateinit var password: String

    fun get(fn: String, fd: String, fp: String): String? {
        login(login, password)
        val uri = urlGet(fn, fd, fp)
        val headers = HttpHeaders()
        headers.add("device-id", "")
        headers.add("device-os", "")
        headers.add("Authorization", getAuthHeader(login, password))

        val responseEntity = RestTemplate().exchange(
                URI.create(uri),
                HttpMethod.GET,
                HttpEntity<String>(headers),
                String::class.java
        )
        if (responseEntity.statusCode == HttpStatus.OK) {
            return responseEntity.body
        }
        return null
    }

    fun getWithPhoneAndPassword(fn: String, fd: String, fp: String, phoneUser: String, passwordUser: String): String? {
        login(phoneUser, passwordUser)
        val uri = urlGet(fn, fd, fp)
        val headers = HttpHeaders()
        headers.add("device-id", "")
        headers.add("device-os", "")
        headers.add("Authorization", getAuthHeader(phoneUser, passwordUser))

        val responseEntity = RestTemplate().exchange(
                URI.create(uri),
                HttpMethod.GET,
                HttpEntity<String>(headers),
                String::class.java
        )
        if (responseEntity.statusCode == HttpStatus.OK) {
            return responseEntity.body
        }
        return null
    }

    fun getReceiptExists(fn: String, fd: String, fp: String, time: String, money: Float): Boolean {
        val moneyForUrl: Int = (money * 100).toInt()
        val uri = "$HOST/v1/ofds/*/inns/*/fss/$fn/operations/1/tickets/$fd?fiscalSign=$fp&date=$time&sum=$moneyForUrl"
        val headers = HttpHeaders()

        val responseEntity = try {
            RestTemplate().exchange(
                    URI.create(uri),
                    HttpMethod.GET,
                    HttpEntity<String>(headers),
                    String::class.java
            )
        } catch (e: HttpServerErrorException) {
            if (e.statusCode == HttpStatus.NOT_ACCEPTABLE) {
                return false
            }

            throw e
        }

        return responseEntity.statusCode == HttpStatus.NO_CONTENT
    }

    fun passwordRestore(number: String) {
        val headers = HttpHeaders()
        headers.add("Content-Type", "application/json; charset=UTF-8")
        RestTemplate().exchange(
                URI("$HOST/v1/mobile/users/restore"),
                HttpMethod.POST,
                HttpEntity("""{"phone":"$number"}""", headers),
                String::class.java
        )
    }

    fun signUp(email: String, name: String, phone: String) {
        val headers = HttpHeaders()
        headers.add("Content-Type", "application/json; charset=UTF-8")
        RestTemplate().exchange(
                URI("$HOST/v1/mobile/users/signup"),
                HttpMethod.POST,
                HttpEntity("""{"email":"$email","name":"$name","phone":"$phone"}""", headers),
                String::class.java
        )
    }

    fun login(login: String, password: String) {
        val headers = HttpHeaders()
        headers.add("Content-Type", "application/json; charset=UTF-8")
        headers.add("Authorization", getAuthHeader(login, password))
        try {
            RestTemplate().exchange(
                    URI("$HOST/v1/mobile/users/login"),
                    HttpMethod.GET,
                    HttpEntity<String>(headers),
                    String::class.java
            )
        } catch (e: HttpClientErrorException) {
            if (e.statusCode == HttpStatus.FORBIDDEN) {
                throw AuthorizationFailedException(login, e)
            } else {
                throw e
            }
        }
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
