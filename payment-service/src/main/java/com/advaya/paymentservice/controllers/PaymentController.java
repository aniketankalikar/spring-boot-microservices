package com.advaya.paymentservice.controllers;

import com.advaya.paymentservice.dtos.InitiatePaymentRequest;
import com.advaya.paymentservice.services.PaymentService;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private PaymentService paymentService;

    @PostMapping("/initiate")
    public String initiatePayment(@RequestBody InitiatePaymentRequest initiatePaymentRequest)
    {

        String url = null;
        try {
            url = paymentService.initiatePayment(initiatePaymentRequest.getOrderId(), initiatePaymentRequest.getAmount());
        } catch (RazorpayException e) {
            throw new RuntimeException(e);
        }
        return url;
    }
}
