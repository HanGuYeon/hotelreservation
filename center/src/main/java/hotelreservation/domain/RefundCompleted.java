package hotelreservation.domain;

import hotelreservation.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class RefundCompleted extends AbstractEvent {

    private Long id;
    private String userId;
    private String roomId;
    private String reserveDt;
    private String cancleDt;
    private String reqType;
}
