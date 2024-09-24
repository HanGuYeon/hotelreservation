package hotelreservation.domain;

import hotelreservation.domain.*;
import hotelreservation.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class RefundCompleted extends AbstractEvent {

    private Long id;
    private String userId;
    private String roomId;
    private String reserveDt;
    private String cancleDt;
    private String reqType;

    public RefundCompleted(Payment aggregate) {
        super(aggregate);
    }

    public RefundCompleted() {
        super();
    }
}
//>>> DDD / Domain Event
