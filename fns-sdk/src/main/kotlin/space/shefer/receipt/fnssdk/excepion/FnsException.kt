package space.shefer.receipt.fnssdk.excepion

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

open class FnsException(message: String, cause: Throwable? = null) : RuntimeException(message, cause)

class ReceiptNotFoundException(val fn: String, val fd: String, val fp: String)
    : FnsException("fn=$fn, fd=$fd, fp=$fp")

@ResponseStatus(code = HttpStatus.FORBIDDEN)
class AuthorizationFailedException(login: String, cause: Throwable? = null)
    : FnsException("Login with phone $login failed", cause)
