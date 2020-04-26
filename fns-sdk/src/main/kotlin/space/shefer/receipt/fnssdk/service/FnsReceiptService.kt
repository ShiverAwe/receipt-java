package space.shefer.receipt.fnssdk.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import space.shefer.receipt.fnssdk.webclient.FnsReceiptWebClient
import java.lang.Exception
import java.util.concurrent.TimeUnit

@Service
class FnsReceiptService {

    @Autowired
    lateinit var fnsReceiptService: FnsReceiptWebClient

    fun getReceiptExists(fn: String, fd: String, fp: String, time: String, money: Float): String? {

        var dataReceipt: String? = null
        if (fnsReceiptService.getReceiptExists(fn, fd, fp, time, money)) {
            for (i in 1..10) {
                dataReceipt = fnsReceiptService.get(fn, fd, fp)
                if (dataReceipt != null) {
                    break
                }
                if ((i == 10) && (dataReceipt == null)) throw Exception("Receipt couldn't found, try again")
                TimeUnit.SECONDS.sleep(1)
            }
        }
        return dataReceipt
    }

}
