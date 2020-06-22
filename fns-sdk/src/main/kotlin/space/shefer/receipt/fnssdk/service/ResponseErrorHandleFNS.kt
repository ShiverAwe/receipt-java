package space.shefer.receipt.fnssdk.service

import org.springframework.http.HttpStatus
import org.springframework.http.client.ClientHttpResponse
import org.springframework.web.client.ResponseErrorHandler
import java.io.IOException
import java.lang.UnsupportedOperationException

class ResponseErrorHandleFNS : ResponseErrorHandler {

    @Throws(IOException::class)
    override fun handleError(httpResponse: ClientHttpResponse) {

        if ((httpResponse.statusCode != HttpStatus.CONFLICT)
                && (httpResponse.statusCode != HttpStatus.BAD_REQUEST)
                && (httpResponse.statusCode != HttpStatus.INTERNAL_SERVER_ERROR)) {
            throw UnsupportedOperationException("Unexpected error")
        }


        if (httpResponse.statusCode == HttpStatus.CONFLICT && httpResponse.body.toString() != "user exists") {
            throw UnsupportedOperationException("Unexpected error");
        } else if (httpResponse.statusCode == HttpStatus.BAD_REQUEST && !httpResponse.body.toString().contains("Object didn't pass validation for format email")) {
            throw UnsupportedOperationException("Unexpected error")
        } else if (httpResponse.statusCode == HttpStatus.INTERNAL_SERVER_ERROR
                && httpResponse.body.toString() != "failed with code 20101") {
            throw UnsupportedOperationException("Unexpected error")
        }

    }

    override fun hasError(response: ClientHttpResponse): Boolean {
        return false
    }

}
