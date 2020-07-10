package space.shefer.receipt.fnssdk.service

import org.springframework.http.client.ClientHttpResponse
import org.springframework.web.client.ResponseErrorHandler
import java.io.IOException

class ResponseErrorHandleFNS : ResponseErrorHandler {

    @Throws(IOException::class)
    override fun handleError(httpResponse: ClientHttpResponse) {

    }

    override fun hasError(response: ClientHttpResponse): Boolean {
        return false
    }
}
