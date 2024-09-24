package hotelreservation.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

//<<< EDA / CQRS
@Entity
@Table(name = "ReserveHistory_table")
@Data
public class ReserveHistory {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String userId;
    private String roomId;
    private String ammount;
    private String reserveDt;
    private String cancleDt;
    private String status;
    private String paymentMethod;
    private Integer qty;
    private String reqType;
}
