package hotelreservation.domain;

import hotelreservation.RoomApplication;
import hotelreservation.domain.CancleCompleted;
import hotelreservation.domain.OutOfStock;
import hotelreservation.domain.ReserveCompleted;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Room_table")
@Data
//<<< DDD / Aggregate Root
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;

    private String roomId;

    private Integer qty;

    private String status;

    private String paymentMethod;

    private String ammount;

    private String reqType;

    @PostPersist
    public void onPostPersist() {}

    @PostUpdate
    public void onPostUpdate() {
        ReserveCompleted reserveCompleted = new ReserveCompleted(this);
        reserveCompleted.publishAfterCommit();

        CancleCompleted cancleCompleted = new CancleCompleted(this);
        cancleCompleted.publishAfterCommit();

        OutOfStock outOfStock = new OutOfStock(this);
        outOfStock.publishAfterCommit();
    }

    public static RoomRepository repository() {
        RoomRepository roomRepository = RoomApplication.applicationContext.getBean(
            RoomRepository.class
        );
        return roomRepository;
    }

    public void reserveComplete() {
        //implement business logic here:

    }

    public void cancleComplete() {
        //implement business logic here:

    }

    //<<< Clean Arch / Port Method
    public static void startReserve(PaymentCompleted paymentCompleted) {
        //implement business logic here:

        /** Example 1:  new item 
        Room room = new Room();
        repository().save(room);

        OutOfStock outOfStock = new OutOfStock(room);
        outOfStock.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(paymentCompleted.get???()).ifPresent(room->{
            
            room // do something
            repository().save(room);

            OutOfStock outOfStock = new OutOfStock(room);
            outOfStock.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void startCancle(RefundCompleted refundCompleted) {
        //implement business logic here:

        /** Example 1:  new item 
        Room room = new Room();
        repository().save(room);

        */

        /** Example 2:  finding and process
        
        repository().findById(refundCompleted.get???()).ifPresent(room->{
            
            room // do something
            repository().save(room);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
