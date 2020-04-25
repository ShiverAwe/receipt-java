package space.shefer.receipt.fnssdk.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import space.shefer.receipt.fnssdk.webclient.FnsReceiptWebClient
import javax.annotation.PostConstruct

@Service
class FnsReceiptService {

    @Autowired
    lateinit var fnsReceiptService: FnsReceiptWebClient

    fun getReceiptExists(fn: String, fd: String, fp: String, time: String, money: Float): String? {
        var dataReceipt: String? = null
        if (fnsReceiptService.getReceiptExists(fn, fd, fp, time, money)) {
            while (dataReceipt == null) {
                dataReceipt = fnsReceiptService.get(fn, fd, fp)
            }
        }
        return dataReceipt
    }
}
