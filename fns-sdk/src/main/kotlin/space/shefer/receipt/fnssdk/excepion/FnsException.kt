package space.shefer.receipt.fnssdk.excepion

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

open class FnsException(message: String, cause: Throwable? = null) : RuntimeException(message, cause)

class ReceiptNotFoundException(val fn: String, val fd: String, val fp: String)
    : FnsException("fn=$fn, fd=$fd, fp=$fp")

@ResponseStatus(code = HttpStatus.FORBIDDEN)
class AuthorizationFailedException(login: String, cause: Throwable? = null)
    : FnsException("Login with phone $login failed", cause)

@ResponseStatus(code = HttpStatus.CONFLICT)
class UserAlreadyExistsException(userName: String)
    : FnsException("This user $userName exists")

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
class IncorrectEmailException(email: String)
    : FnsException("Object didn't pass validation for format email: $email")

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
class IncorrectPhoneException(phone: String)
    : FnsException("Phone $phone is incorrect")

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
class UnexpectedHttpException()
    : FnsException("Unexpected error")

@ResponseStatus(code = HttpStatus.NOT_FOUND)
class ErrorToken()
    : FnsException("Token not found")
