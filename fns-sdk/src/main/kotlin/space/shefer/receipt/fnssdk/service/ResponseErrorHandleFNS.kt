package space.shefer.receipt.fnssdk.service

import org.springframework.http.HttpStatus
import org.springframework.http.client.ClientHttpResponse
import org.springframework.web.client.ResponseErrorHandler
import java.io.IOException

class ResponseErrorHandleFNS : ResponseErrorHandler {

    @Throws(IOException::class)
    override fun handleError(httpResponse: ClientHttpResponse) {
        when {
            httpResponse.statusCode
                    .series() == HttpStatus.Series.SERVER_ERROR -> {

            }
            httpResponse.statusCode
                    .series() == HttpStatus.Series.CLIENT_ERROR -> {

            }
            httpResponse.statusCode == HttpStatus.NOT_FOUND -> {

            }
        }
    }

    override fun hasError(response: ClientHttpResponse): Boolean {
        return false
    }

}
