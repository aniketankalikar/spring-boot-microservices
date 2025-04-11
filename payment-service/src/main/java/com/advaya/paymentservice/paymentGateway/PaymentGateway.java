package com.advaya.paymentservice.paymentGateway;

import com.razorpay.RazorpayException;

public interface PaymentGateway {

    public String generatePaymentLink(Long orderId, Long amount);
}
