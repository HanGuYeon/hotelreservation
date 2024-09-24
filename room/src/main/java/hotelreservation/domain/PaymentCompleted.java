package hotelreservation.domain;

import hotelreservation.domain.*;
import hotelreservation.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class PaymentCompleted extends AbstractEvent {

    private Long id;
    private String userId;
    private String roomId;
    private String reserveDt;
    private String paymentMethod;
    private String ammount;
    private String reqType;
}
