package hotelreservation.domain;

import hotelreservation.domain.*;
import hotelreservation.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class ReserveRequested extends AbstractEvent {

    private Long id;
    private String userId;
    private String roomId;
    private String ammount;
    private String reserveDt;
    private String reqType;
}
