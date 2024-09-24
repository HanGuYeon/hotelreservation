package hotelreservation.domain;

import hotelreservation.ReserveApplication;
import hotelreservation.domain.CancleRequested;
import hotelreservation.domain.ReserveRequested;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Reserve_table")
@Data
//<<< DDD / Aggregate Root
public class Reserve {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;

    private String roomId;

    private Integer qty;

    private String ammount;

    private String reserveDt;

    private String cancleDt;

    private String status;

    private String reqType;

    @PostPersist
    public void onPostPersist() {
        ReserveRequested reserveRequested = new ReserveRequested(this);
        reserveRequested.publishAfterCommit();

        CancleRequested cancleRequested = new CancleRequested(this);
        cancleRequested.publishAfterCommit();
    }

    public static ReserveRepository repository() {
        ReserveRepository reserveRepository = ReserveApplication.applicationContext.getBean(
            ReserveRepository.class
        );
        return reserveRepository;
    }

    public void requestReserve() {
        //implement business logic here:

    }

    public void requestCancle() {
        //implement business logic here:

    }

    //<<< Clean Arch / Port Method
    public static void updateStatus(ReserveCompleted reserveCompleted) {
        //implement business logic here:

        /** Example 1:  new item 
        Reserve reserve = new Reserve();
        repository().save(reserve);

        */

        /** Example 2:  finding and process
        
        repository().findById(reserveCompleted.get???()).ifPresent(reserve->{
            
            reserve // do something
            repository().save(reserve);


         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void updateStatus(CancleCompleted cancleCompleted) {
        //implement business logic here:

        /** Example 1:  new item 
        Reserve reserve = new Reserve();
        repository().save(reserve);

        */

        /** Example 2:  finding and process
        
        repository().findById(cancleCompleted.get???()).ifPresent(reserve->{
            
            reserve // do something
            repository().save(reserve);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
