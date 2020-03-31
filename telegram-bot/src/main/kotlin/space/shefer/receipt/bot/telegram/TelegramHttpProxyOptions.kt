package space.shefer.receipt.bot.telegram

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

@Configuration
class TelegramHttpProxyOptions {

    @Value("\${telegram.proxy.enabled:false}")
    var enabled: Boolean = false
    @Value("\${telegram.proxy.host:#{null}}")
    var host: String? = null
    @Value("\${telegram.proxy.port:#{null}}")
    var port: Int? = null

    @PostConstruct
    private fun validate(){
        if (enabled) {
            check(host != null) {"If telegram proxy enabled, than host should be specified"}
            check(port != null) {"If telegram proxy enabled, than port should be specified"}
        }
    }

}
