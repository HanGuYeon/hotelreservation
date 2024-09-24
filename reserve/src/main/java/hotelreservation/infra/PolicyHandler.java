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
    ReserveRepository reserveRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ReserveCompleted'"
    )
    public void wheneverReserveCompleted_UpdateStatus(
        @Payload ReserveCompleted reserveCompleted
    ) {
        ReserveCompleted event = reserveCompleted;
        System.out.println(
            "\n\n##### listener UpdateStatus : " + reserveCompleted + "\n\n"
        );

        // Sample Logic //
        Reserve.updateStatus(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='CancleCompleted'"
    )
    public void wheneverCancleCompleted_UpdateStatus(
        @Payload CancleCompleted cancleCompleted
    ) {
        CancleCompleted event = cancleCompleted;
        System.out.println(
            "\n\n##### listener UpdateStatus : " + cancleCompleted + "\n\n"
        );

        // Sample Logic //
        Reserve.updateStatus(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
