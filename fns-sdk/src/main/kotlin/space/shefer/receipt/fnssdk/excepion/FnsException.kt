package space.shefer.receipt.fnssdk.excepion

open class FnsException(message: String, cause: Throwable? = null) : RuntimeException(message, cause)

class ReceiptNotFoundException(val fn: String, val fp: String, val fd: String)
    : FnsException("fn=$fn, fd=$fd, fp=$fp")

class AuthorizationFailedException(login: String, cause: Throwable? = null)
    : FnsException("Login with phone $login failed", cause)