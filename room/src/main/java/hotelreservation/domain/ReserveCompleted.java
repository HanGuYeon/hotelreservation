package hotelreservation.domain;

import hotelreservation.domain.*;
import hotelreservation.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class ReserveCompleted extends AbstractEvent {

    private Long id;
    private String userId;
    private String roomId;
    private Integer qty;
    private String status;
    private String reqType;

    public ReserveCompleted(Room aggregate) {
        super(aggregate);
    }

    public ReserveCompleted() {
        super();
    }
}
//>>> DDD / Domain Event
