package com.advaya.paymentservice.services;

import com.advaya.paymentservice.paymentGateway.PaymentGateway;
import com.advaya.paymentservice.paymentGateway.RazorpayPaymentGateway;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private PaymentGateway paymentGateway;

    public String initiatePayment(Long orderId, Long amount) throws RazorpayException
    {

        //Make a call to Payment Gatway to generate the Payment Link
        paymentGateway.generatePaymentLink(orderId,amount);


        return null;
    }
}
