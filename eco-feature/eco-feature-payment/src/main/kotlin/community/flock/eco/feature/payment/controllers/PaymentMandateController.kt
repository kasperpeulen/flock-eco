package community.flock.eco.feature.payment.controllers

import community.flock.eco.feature.payment.model.PaymentMandate
import community.flock.eco.feature.payment.model.PaymentTransaction
import community.flock.eco.feature.payment.repositories.PaymentMandateRepository
import community.flock.eco.feature.payment.repositories.PaymentTransactionRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/payment/mandates")
class PaymentMandateController(
        private val paymentMandateRepository: PaymentMandateRepository,
        private val paymentTransactionRepository: PaymentTransactionRepository
) {

    @GetMapping()
    @PreAuthorize("hasAuthority('PaymentMandateAuthority.READ')")
    fun findByAll(pageable: Pageable): Page<PaymentMandate> = paymentMandateRepository.findAll(pageable)

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('PaymentMandateAuthority.READ')")
    fun findById(@PathVariable id: Long): PaymentMandate? = paymentMandateRepository.findById(id)
            .orElse(null)

    @GetMapping("/{id}/transactions")
    @PreAuthorize("hasAuthority('PaymentMandateAuthority.READ')")
    fun findByIdTransactions(@PathVariable id: Long): List<PaymentTransaction> = paymentMandateRepository.findById(id)
            .map { paymentTransactionRepository.findByMandate(it) }
            .orElse(listOf())

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('PaymentMandateAuthority.READ')")
    fun update(@PathVariable id: Long, @RequestBody form: PaymentMandate): PaymentMandate? {
        val mandate = paymentMandateRepository.findById(id)
        return when (mandate.isPresent) {
            true -> paymentMandateRepository.save(form.copy(id = mandate.get().id))
            else -> null
        }
    }

}