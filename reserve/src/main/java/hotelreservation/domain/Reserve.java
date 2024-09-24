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
        Date today = new Date();
        System.out.println("# 사용자 요청");
        System.out.println("[" + today + "] " + Thread.currentThread().getStackTrace()[1].getClassName());
        System.out.println("[" + today + "] " + Thread.currentThread().getStackTrace()[1].getMethodName());
        System.out.println("[" + today + "] " + "reqType(0 예약, 1 취소) 검증하여 예약/취소 요청 : " + this.reqType);
        if(this.reqType.equals("0")){
            requestReserve();
        } else {
            requestCancle();
        }
    

 
    }

    public static ReserveRepository repository() {
        ReserveRepository reserveRepository = ReserveApplication.applicationContext.getBean(
            ReserveRepository.class
        );
        return reserveRepository;
    }

    public void requestReserve() {
        Date today = new Date();
        System.out.println("# reqType : " + this.reqType + " 예약 이벤트 발생");
        System.out.println("[" + today + "] " + Thread.currentThread().getStackTrace()[1].getClassName());
        System.out.println("[" + today + "] " + Thread.currentThread().getStackTrace()[1].getMethodName());
        ReserveRequested reserveRequested = new ReserveRequested(this);
        reserveRequested.publishAfterCommit();

    }

    public void requestCancle() {
        Date today = new Date();
        System.out.println("# reqType : " + this.reqType + " 취소 이벤트 발생");
        System.out.println("[" + today + "] " + Thread.currentThread().getStackTrace()[1].getClassName());
        System.out.println("[" + today + "] " + Thread.currentThread().getStackTrace()[1].getMethodName());
        CancleRequested cancleRequested = new CancleRequested(this);
        cancleRequested.publishAfterCommit();

    }

    //<<< Clean Arch / Port Method
    public static void updateStatus(ReserveCompleted reserveCompleted) {
      
        Date today = new Date();
        System.out.println("# 예약(3) 예약 완료 -> 객실 상태 변경");
        System.out.println("[" + today + "] " + Thread.currentThread().getStackTrace()[1].getClassName());
        System.out.println("[" + today + "] " + Thread.currentThread().getStackTrace()[1].getMethodName());

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void updateStatus(CancleCompleted cancleCompleted) {
        Date today = new Date();
        System.out.println("# 취소(3) 취소 완료 -> 객실 상태 변경");
        System.out.println("[" + today + "] " + Thread.currentThread().getStackTrace()[1].getClassName());
        System.out.println("[" + today + "] " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
