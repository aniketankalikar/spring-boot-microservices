package com.advaya.paymentservice.paymentGateway;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;

@Primary
public class StripePaymentGateway implements PaymentGateway {

    @Value("${STRIPE_KEY_SECRET}")
    private String stripeSecretKey;

    @Override
    public String generatePaymentLink(Long orderId, Long amount) {

        Stripe.apiKey = stripeSecretKey;
        String url = "";

        Stripe.apiKey = "sk_test_tR3PYbcVNZZ796tH88S4VQ2u";
        try {

            PriceCreateParams priceParams =
                    PriceCreateParams.builder()
                            .setCurrency("inr")
                            .setUnitAmount(1000L)
                            .build();

            Price price = Price.create(priceParams);

            PaymentLinkCreateParams params = PaymentLinkCreateParams.builder()
                    .addLineItem(
                            PaymentLinkCreateParams.LineItem.builder()
                                    .setPrice(price.getId())
                                    .setQuantity(2L)
                                    .build()
                    )
                    .setAfterCompletion(
                            PaymentLinkCreateParams.AfterCompletion.builder()
                                    .setRedirect(
                                            PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                                    .setUrl("https://www.sacler.com")
                                                    .build()
                                    )
                                    .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                    .build()
                    )
                    .build();

            PaymentLink paymentLink = PaymentLink.create(params);
            url = paymentLink.getUrl();
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
        return url;
    }
}
