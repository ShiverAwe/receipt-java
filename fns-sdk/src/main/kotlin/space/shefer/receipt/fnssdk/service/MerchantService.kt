package space.shefer.receipt.fnssdk.service

import space.shefer.receipt.fnssdk.primainform.MerchantSuggestion

class MerchantService {

    fun getMerchantFromPrimaInformOrNone(merchantSuggestions: MerchantSuggestion, inn: String): MerchantSuggestion? {
        merchantSuggestions.suggestions = merchantSuggestions.suggestions.filter { it.data.inn == inn }
        return merchantSuggestions
    }

}


