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
        Date today = new Date();
        System.out.println("# 예약(3) 외부 시스템에 예약 요청");
        System.out.println("[" + today + "] " + Thread.currentThread().getStackTrace()[1].getClassName());
        System.out.println("[" + today + "] " + Thread.currentThread().getStackTrace()[1].getMethodName());

        System.out.println("# 예약을 위한 객실 잔여 수 확인");

        ReserveCompleted reserveCompleted = new ReserveCompleted();
        reserveCompleted.publishAfterCommit();

        OutOfStock outOfStock = new OutOfStock();
        outOfStock.publishAfterCommit();

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void startCancle(RefundCompleted refundCompleted) {
        Date today = new Date();
        System.out.println("# 취소(3) 외부 시스템에 취소 요청");
        System.out.println("[" + today + "] " + Thread.currentThread().getStackTrace()[1].getClassName());
        System.out.println("[" + today + "] " + Thread.currentThread().getStackTrace()[1].getMethodName());

        CancleCompleted cancleCompleted = new CancleCompleted();
        cancleCompleted.publishAfterCommit();


     

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
