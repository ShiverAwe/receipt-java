package space.shefer.receipt.fnssdk.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import space.shefer.receipt.fnssdk.excepion.AuthorizationFailedException
import space.shefer.receipt.fnssdk.excepion.ReceiptNotFoundException
import space.shefer.receipt.fnssdk.webclient.FnsReceiptWebClient
import java.util.concurrent.TimeUnit

@Service
class FnsService {

    @Autowired
    lateinit var fnsReceiptWebClient: FnsReceiptWebClient

    fun getReceiptExists(fn: String, fd: String, fp: String, time: String,
                         money: Float, phone: String?, password: String?): String? {
        if (fnsReceiptWebClient.getReceiptExists(fn, fd, fp, time, money)) {
            for (i in 1..MAX_ATTEMPTS) {
                try {
                    val dataReceipt = if (phone == null || password == null) {
                        fnsReceiptWebClient.get(fn, fd, fp)
                    } else {
                        fnsReceiptWebClient.get(fn, fd, fp, phone, password)
                    }

                    if (dataReceipt != null) {
                        return dataReceipt
                    }
                } catch (e: AuthorizationFailedException) {
                    throw e
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                TimeUnit.SECONDS.sleep(1)
            }
        }
        throw ReceiptNotFoundException(fn, fd, fp)
    }

    companion object {
        private const val MAX_ATTEMPTS = 10
    }

}
