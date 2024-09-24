package hotelreservation.domain;

import hotelreservation.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class ReserveCompleted extends AbstractEvent {

    private Long id;
    private String userId;
    private String roomId;
    private Integer qty;
    private String status;
    private String reqType;
}
