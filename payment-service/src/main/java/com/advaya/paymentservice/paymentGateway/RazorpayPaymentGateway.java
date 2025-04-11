package com.advaya.paymentservice.paymentGateway;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RazorpayPaymentGateway implements PaymentGateway{


    private RazorpayClient razorpayClient;
    @Override
    public String generatePaymentLink(Long orderId, Long amount) {

        // Call the Razorpay API to generate Link
        JSONObject paymentLinkRequest = new JSONObject();

        try {
           PaymentLink paymentLink =  razorpayClient.paymentLink.create(paymentLinkRequest);
        } catch (RazorpayException e) {
            throw new RuntimeException(e);
        }

        return paymentLinkRequest.get("short_url").toString();
    }
}
