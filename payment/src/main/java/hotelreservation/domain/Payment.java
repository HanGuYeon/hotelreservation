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
        System.out.println("# 예약(2) 결제 진행");
        System.out.println("[" + today + "] " + Thread.currentThread().getStackTrace()[1].getClassName());
        System.out.println("[" + today + "] " + Thread.currentThread().getStackTrace()[1].getMethodName());
        try{
            Thread.sleep(1000);
        } catch (Exception e){

        }
        
        PaymentCompleted paymentCompleted = new PaymentCompleted();
        paymentCompleted.publishAfterCommit();


    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void startRefund(CancleRequested cancleRequested) {
        Date today = new Date();
        System.out.println("# 취소(2) 환불 처리");
        System.out.println("[" + today + "] " + Thread.currentThread().getStackTrace()[1].getClassName());
        System.out.println("[" + today + "] " + Thread.currentThread().getStackTrace()[1].getMethodName());
        try{
            Thread.sleep(1000);
        } catch (Exception e){

        }
        
        RefundCompleted refundCompleted = new RefundCompleted();
        refundCompleted.publishAfterCommit();

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void startRefund(OutOfStock outOfStock) {
        Date today = new Date();
        System.out.println("# 예약(4) 잔여 객실이 없으므로 결제 환불 진행");
        System.out.println("[" + today + "] " + Thread.currentThread().getStackTrace()[1].getClassName());
        System.out.println("[" + today + "] " + Thread.currentThread().getStackTrace()[1].getMethodName());
        try{
            Thread.sleep(1000);
        } catch (Exception e){

        }
    
    }

}
//>>> DDD / Aggregate Root
