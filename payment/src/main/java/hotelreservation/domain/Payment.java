package hotelreservation.domain;

import hotelreservation.PaymentApplication;
import hotelreservation.domain.PaymentCompleted;
import hotelreservation.domain.RefundCompleted;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Payment_table")
@Data
//<<< DDD / Aggregate Root
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;

    private String roomId;

    private String reserveDt;

    private String cancleDt;

    private String paymentMethod;

    private String ammount;

    private Integer qty;

    private String reqType;

    @PostPersist
    public void onPostPersist() {

    }

    public static PaymentRepository repository() {
        PaymentRepository paymentRepository = PaymentApplication.applicationContext.getBean(
            PaymentRepository.class
        );
        return paymentRepository;
    }

    //<<< Clean Arch / Port Method
    public static void startPayment(ReserveRequested reserveRequested) {
        Date today = new Date();
        System.out.println("# 예약(1) 결제 진행");
        System.out.println("[" + today + "] " + Thread.currentThread().getStackTrace()[1].getClassName());
        System.out.println("[" + today + "] " + Thread.currentThread().getStackTrace()[1].getMethodName());

        
        PaymentCompleted paymentCompleted = new PaymentCompleted();
        paymentCompleted.publishAfterCommit();


    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void startRefund(CancleRequested cancleRequested) {
        Date today = new Date();
        System.out.println("# 취소(1) 결제 환불 처리");
        System.out.println("[" + today + "] " + Thread.currentThread().getStackTrace()[1].getClassName());
        System.out.println("[" + today + "] " + Thread.currentThread().getStackTrace()[1].getMethodName());

        
        RefundCompleted refundCompleted = new RefundCompleted();
        refundCompleted.publishAfterCommit();

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void startRefund(OutOfStock outOfStock) {
    
    }

}
//>>> DDD / Aggregate Root
