package hotelreservation.domain;

import hotelreservation.domain.*;
import hotelreservation.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class CancleRequested extends AbstractEvent {

    private Long id;
    private String userId;
    private String roomId;
    private Integer qty;
    private String ammount;
    private String reserveDt;
    private String cancleDt;
    private String reqType;

    public CancleRequested(Reserve aggregate) {
        super(aggregate);
    }

    public CancleRequested() {
        super();
    }
}
//>>> DDD / Domain Event
