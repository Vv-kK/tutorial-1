package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Payment addPayment(String id, String method, Map<String, String> paymentData, Order order) {
        return null;
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        return null;
    }

    @Override
    public Payment getPayment(String paymentId) {
        return null;
    }

    @Override
    public List<Payment> getAllPayment() {
        return null;
    }
}
