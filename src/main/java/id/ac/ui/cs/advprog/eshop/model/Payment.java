package id.ac.ui.cs.advprog.eshop.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;

@Builder
@Getter
public class Payment {
    String id;
    String method;
    String status;
    Map<String, String> paymentData;
    Order order;

    public Payment(String id, String method, String status, Map<String, String> paymentData, Order order){
        this.id = id;
        this.method = method;
        this.setStatus(status);
        this.paymentData = paymentData;
        this.order = order;
    }

    public void setStatus(String status){
        String[] statusList = {"SUCCESS", "REJECTED"};
        if (Arrays.stream(statusList).noneMatch(item -> (item.equals(status)))){
            throw new IllegalArgumentException();
        } else {
            this.status = status;
        }
    }
}
