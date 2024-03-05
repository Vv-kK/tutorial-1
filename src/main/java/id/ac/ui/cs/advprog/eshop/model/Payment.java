package id.ac.ui.cs.advprog.eshop.model;

import enums.OrderStatus;
import enums.PaymentMethod;
import enums.PaymentStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;

@Getter
public class Payment {
    String id;
    String method;
    String status;
    Map<String, String> paymentData;
    Order order;

    public Payment(String id, String method, Map<String, String> paymentData, Order order){
        this.id = id;
        this.paymentData = paymentData;
        this.order = order;

        if (PaymentMethod.contains(method)) {
            this.method = method;
        } else {
            throw new IllegalArgumentException();
        }
        this.validasiMethod(method, paymentData);
    }

    public void setStatus(String status){
        if (PaymentStatus.contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void validasiMethod(String method, Map<String, String> paymentData){

    }
}
