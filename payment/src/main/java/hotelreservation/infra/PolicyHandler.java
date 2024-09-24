package hotelreservation.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import hotelreservation.config.kafka.KafkaProcessor;
import hotelreservation.domain.*;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    PaymentRepository paymentRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ReserveRequested'"
    )
    public void wheneverReserveRequested_StartPayment(
        @Payload ReserveRequested reserveRequested
    ) {
        ReserveRequested event = reserveRequested;
        System.out.println(
            "\n\n##### listener StartPayment : " + reserveRequested + "\n\n"
        );

        // Sample Logic //
        Payment.startPayment(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='CancleRequested'"
    )
    public void wheneverCancleRequested_StartRefund(
        @Payload CancleRequested cancleRequested
    ) {
        CancleRequested event = cancleRequested;
        System.out.println(
            "\n\n##### listener StartRefund : " + cancleRequested + "\n\n"
        );

        // Sample Logic //
        Payment.startRefund(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OutOfStock'"
    )
    public void wheneverOutOfStock_StartRefund(@Payload OutOfStock outOfStock) {
        OutOfStock event = outOfStock;
        System.out.println(
            "\n\n##### listener StartRefund : " + outOfStock + "\n\n"
        );

        // Sample Logic //
        Payment.startRefund(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
