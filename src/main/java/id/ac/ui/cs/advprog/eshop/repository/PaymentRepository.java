package id.ac.ui.cs.advprog.eshop.repository;

import org.springframework.stereotype.Repository;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentRepository {
    private List<Payment> payments = new ArrayList<>();

    public Payment save(Payment payment) {
        return null;
    }

    public Payment findById(String id) {
        return null;
    }

    public List<Payment> getAllPayment() {
        return null;
    }
}
