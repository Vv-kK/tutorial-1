package id.ac.ui.cs.advprog.eshop.repository;

import org.springframework.stereotype.Repository;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentRepository {
    private List<Payment> payments = new ArrayList<>();

    public Payment save(Payment payment) {
        int i = 0;
        for (Payment savedPayment : payments) {
            if (savedPayment.getId().equals(payment.getId())) {
                payments.remove(i);
                payments.add(i, payment);
                return payment;
            }
            i += 1;
        }
        payments.add(payment);
        return payment;
    }

    public Payment findById(String id) {
        for (Payment payment : payments) {
            if (payment.getId().equals(id)) {
                return payment;
            }
        }
        return null;
    }

    public List<Payment> getAllPayment() {
        return payments;
    }
}
