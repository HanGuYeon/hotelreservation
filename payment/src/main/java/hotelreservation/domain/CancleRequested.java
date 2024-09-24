package hotelreservation.domain;

import hotelreservation.domain.*;
import hotelreservation.infra.AbstractEvent;
import java.util.*;
import lombok.*;

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
}
