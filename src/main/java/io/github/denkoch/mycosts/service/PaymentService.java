package io.github.denkoch.mycosts.service;

import io.github.denkoch.mycosts.entities.Payment;
import io.github.denkoch.mycosts.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;

    public void setPaymentRepository(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Collection<Payment> getPayments() {
        return paymentRepository.findAll();
    }

    public Collection<Payment> getFilteredPayments(LocalDate from, LocalDate to, Integer id) {

        Collection<Payment> paymentsByCategory = id == 0 ? paymentRepository.findAll() : paymentRepository.findByCategory(id);
        Collection<Payment> paymentsByDate = paymentRepository.findByDate(from, to);

        return paymentsByCategory.stream().filter(paymentsByDate::contains).toList();

    }

    public Payment getPayment(Integer id) {
        return paymentRepository.findById(id);
    }

    public void addPayment(Payment payment) {
        paymentRepository.save(getMaxId() + 1, payment);
    }

    public void updatePayment(Integer id, Payment payment) {
        paymentRepository.save(id, payment);
    }

    public void deletePayment(Integer id) {
        paymentRepository.deleteById(id);
    }

    public Integer getMaxId() {
        return paymentRepository.findAll().stream().mapToInt(Payment::getId).max().orElse(0);
    }

    public LocalDate getLowestDate(){
        return paymentRepository.findLowestDate();
    }

}
