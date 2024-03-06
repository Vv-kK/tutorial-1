package id.ac.ui.cs.advprog.eshop.model;

import enums.PaymentMethod;
import enums.PaymentStatus;
import lombok.Getter;

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
        this.validasiPembayaran(method, paymentData);
    }

    public void setStatus(String status){
        if (PaymentStatus.contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void validasiPembayaran(String method, Map<String, String> paymentData){
        if (method.equals(PaymentMethod.VOUCHER_CODE.getValue())){
            String voucherCode = paymentData.get("voucherCode");
            this.validateVoucher(voucherCode);

        } else if (method.equals(PaymentMethod.COD.getValue())){
            String address = paymentData.get("address");
            String deliveryFee = paymentData.get("deliveryFee");
            this.validateCOD(address, deliveryFee);
        }
    }

    public void validateVoucher(String voucherCode){
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
    }

    public void validateCOD(String address, String deliveryFee){
        if (address == null || deliveryFee == null){
            this.setStatus(PaymentStatus.REJECTED.getValue());
            return;
        }
        if (address.isEmpty() || deliveryFee.isEmpty()){
            this.setStatus(PaymentStatus.REJECTED.getValue());
            return;
        }
        this.setStatus(PaymentStatus.SUCCESS.getValue());
    }
}
