package hotelreservation.domain;

import hotelreservation.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class CancleCompleted extends AbstractEvent {

    private Long id;
    private String userId;
    private String roomId;
    private Integer qty;
    private String status;
    private String paymentMethod;
    private String ammount;
    private String reqType;
}
