package id.ac.ui.cs.advprog.eshop.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enums.OrderStatus;
import enums.PaymentMethod;
import enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PaymentTest {
    private List<Product> products;
    private Order order;

    void setUp(){
        this.products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        Product product2 = new Product();
        product2.setProductId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(1);
        this.products.add(product1);
        this.products.add(product2);

        this.order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                this.products, 1708560000L, "Safira Sudrajat", OrderStatus.WAITING_PAYMENT.getValue());
    }

    @Test
    void testCreatePayment(){
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("13652556-012a-4c07-b546-52hb3h1b1n3",
                PaymentMethod.VOUCHER_CODE.getValue(), PaymentStatus.SUCCESS.getValue(), paymentData, order);

        assertSame(this.products, payment.getOrder());
        assertEquals("13652556-012a-4c07-b546-52hb3h1b1n3", payment.getId());
        assertEquals(PaymentMethod.VOUCHER_CODE.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        assertEquals(paymentData.get("voucherCode"), payment.getPaymentData().get("voucherCode"));
    }

    @Test
    void testCreatePaymentInvalidStatus(){
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("13652556-012a-4c07-b546-52hb3h1b1n3",
                    PaymentMethod.VOUCHER_CODE.getValue(), "MEOW", paymentData, order);
        });
    }

    @Test
    void testSetStatusToIvalidStatus(){
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("13652556-012a-4c07-b546-52hb3h1b1n3",
                PaymentMethod.VOUCHER_CODE.getValue(), PaymentStatus.SUCCESS.getValue(), paymentData, order);
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("MEOW"));
    }

    @Test
    void testSetStatusToRejectedStatus(){
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("13652556-012a-4c07-b546-52hb3h1b1n3",
                PaymentMethod.VOUCHER_CODE.getValue(), PaymentStatus.SUCCESS.getValue(), paymentData, order);
        payment.setStatus(PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }
}
