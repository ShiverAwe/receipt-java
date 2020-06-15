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
class UserWasExistException(userName: String, cause: Throwable? = null)
    : FnsException("This user $userName exists", cause)

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
class IncorrectEmailException(email: String, cause: Throwable? = null)
    : FnsException("Object didn't pass validation for format email: $email", cause)

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
class IncorrectPhoneException(phone: String, cause: Throwable? = null)
    : FnsException("Phone $phone is incorrect", cause)
