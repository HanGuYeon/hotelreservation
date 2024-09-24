package hotelreservation.domain;

import hotelreservation.domain.*;
import hotelreservation.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class CancleCompleted extends AbstractEvent {

    private Long id;
    private String userId;
    private String roomId;
    private Integer qty;
    private String status;
    private String paymentMethod;
    private String ammount;
    private String reqType;

    public CancleCompleted(Room aggregate) {
        super(aggregate);
    }

    public CancleCompleted() {
        super();
    }
}
//>>> DDD / Domain Event
