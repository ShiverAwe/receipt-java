package space.shefer.receipt.merchants.config

import org.springframework.context.annotation.Import

@Import(MerchantsResolverConfiguration::class)
annotation class EnableMerchantsResolver
