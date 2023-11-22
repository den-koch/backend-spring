package io.github.denkoch.mycosts.service;

import io.github.denkoch.mycosts.entities.Payment;
import io.github.denkoch.mycosts.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;

    @Autowired
    public void setPaymentRepository(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Collection<Payment> getPayments(Integer userId) {
        return paymentRepository.findAllByUserId(userId);
    }

    public Collection<Payment> getFilteredPayments(Integer userId, LocalDate from, LocalDate to, Integer categoryId) {

        Collection<Payment> paymentsByCategory = categoryId == 0 ? paymentRepository.findAllByUserId(userId) : paymentRepository.findAllByCategoryId(userId, categoryId);
        Collection<Payment> paymentsByDate = paymentRepository.findAllByDate(from, to);

        return paymentsByCategory.stream().filter(paymentsByDate::contains).toList();

    }

    public Payment getPayment(Integer id) {
        return paymentRepository.findById(id);
    }

    public void addPayment(Payment payment) {
        paymentRepository.save(createId(), payment);
    }

    public void updatePayment(Integer id, Payment payment) {
        paymentRepository.save(id, payment);
    }

    public void deletePayment(Integer id) {
        paymentRepository.deleteById(id);
    }

    public Integer createId() {
        return paymentRepository.maxId()+1;
    }

    public LocalDate getLowestDate(){
        return paymentRepository.findLowestDate();
    }

    public LocalDate getHighestDate(){
        return paymentRepository.findHighestDate();
    }

}
