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
        if (method.equals(PaymentMethod.VOUCHER_CODE.getValue())){
            String voucherCode = paymentData.get("voucherCode");
            if (voucherCode == null) {
                this.setStatus(PaymentStatus.REJECTED.getValue());
                return;
            }

            if (voucherCode.length() != 16) {
                this.setStatus(PaymentStatus.REJECTED.getValue());
                return;
            }

            if (!voucherCode.startsWith("ESHOP")) {
                this.setStatus(PaymentStatus.REJECTED.getValue());
                return;
            }

            int digitCount = 0;
            for (char character: voucherCode.toCharArray()) {
                if (Character.isDigit(character)) {
                    digitCount += 1;
                }
            }
            if (digitCount != 8) {
                this.setStatus(PaymentStatus.REJECTED.getValue());
                return;
            }
            this.setStatus(PaymentStatus.SUCCESS.getValue());

        } else if (method.equals(PaymentMethod.COD.getValue())){
            String address = paymentData.get("address");
            String deliveryFee = paymentData.get("deliveryFee");
            if (address == null || deliveryFee == null){
                this.setStatus(PaymentStatus.REJECTED.getValue());
                return;
            }
            if (address.length() == 0 || deliveryFee.length() == 0){
                this.setStatus(PaymentStatus.REJECTED.getValue());
                return;
            }
            this.setStatus(PaymentStatus.SUCCESS.getValue());
        }
    }
}
