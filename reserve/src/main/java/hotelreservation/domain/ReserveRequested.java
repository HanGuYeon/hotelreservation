package hotelreservation.domain;

import hotelreservation.domain.*;
import hotelreservation.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class ReserveRequested extends AbstractEvent {

    private Long id;
    private String userId;
    private String roomId;
    private String ammount;
    private String reserveDt;
    private String reqType;

    public ReserveRequested(Reserve aggregate) {
        super(aggregate);
    }

    public ReserveRequested() {
        super();
    }
}
//>>> DDD / Domain Event
