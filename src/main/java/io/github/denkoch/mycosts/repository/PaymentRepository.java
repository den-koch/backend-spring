package io.github.denkoch.mycosts.repository;

import io.github.denkoch.mycosts.entities.Category;
import io.github.denkoch.mycosts.entities.Payment;
import io.github.denkoch.mycosts.entities.PaymentType;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class PaymentRepository implements ObjectRepository<Payment> {

    private Map<Integer, Payment> repository = new HashMap<>();

    public PaymentRepository(){
        repository.put(1, new Payment(1, LocalDateTime.now(), 300, new Category(1, "Food"), PaymentType.EXPENSE));
        repository.put(2, new Payment(2, LocalDateTime.now(), 56900, new Category(2, "Salary"), PaymentType.INCOME));
        repository.put(3, new Payment(3, LocalDateTime.of(2023, 2, 27, 16, 12), 1234, new Category(3, "Health"), PaymentType.EXPENSE));
        repository.put(4, new Payment(4, LocalDateTime.now(), 3400, new Category(4, "Education"), PaymentType.EXPENSE));
        repository.put(5, new Payment(5, LocalDateTime.of(2023, 11, 1, 7, 39), 120, new Category(5, "Transport"), PaymentType.EXPENSE));
    }

    @Override
    public void save(Integer id, Payment payment) {
        repository.put(id, payment);
    }

    @Override
    public Collection<Payment> findAll() {
        return repository.values();
    }

    public Payment findById(Integer id) {
        return repository.get(id);
    }

    public Collection<Payment> findByCategory(Integer id) {
        return repository.values().stream().filter(payment -> id.equals(payment.getCategory().getId())).collect(Collectors.toList());
    }

    public Collection<Payment> findByDate(LocalDate from, LocalDate to) {
        return repository.values().stream().filter(
                payment -> !payment.getDateTime().toLocalDate().isBefore(from)
                        && !payment.getDateTime().toLocalDate().isAfter(to)).collect(Collectors.toList());

    }

    public LocalDate findLowestDate(){
        return repository.values().stream().map(Payment::getDateTime).min(Comparator.comparing(LocalDateTime::toLocalDate)).get().toLocalDate();
    }

    public LocalDate findHighestDate(){
        return repository.values().stream().map(Payment::getDateTime).max(Comparator.comparing(LocalDateTime::toLocalDate)).get().toLocalDate();
    }


    @Override
    public void deleteById(Integer id) {
        repository.remove(id);
    }

}
