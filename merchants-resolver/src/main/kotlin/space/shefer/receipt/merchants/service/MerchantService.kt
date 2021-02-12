package space.shefer.receipt.merchants.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import space.shefer.receipt.merchants.primainform.MerchantSuggestion
import space.shefer.receipt.merchants.webclient.PrimaInformWebClient

@Service
class MerchantService {

    @Autowired
    lateinit var primaInformWebClient: PrimaInformWebClient

    fun getMerchantFromPrimaInformOrNone(inn: String): MerchantSuggestion? {
        return primaInformWebClient.getSuggestions(inn)
                ?.suggestions
                ?.firstOrNull { it.data.inn == inn }
    }

}


