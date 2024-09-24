package hotelreservation.infra;

import hotelreservation.config.kafka.KafkaProcessor;
import hotelreservation.domain.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ReserveHistoryViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private ReserveHistoryRepository reserveHistoryRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenPaymentCompleted_then_CREATE_1(
        @Payload PaymentCompleted paymentCompleted
    ) {
        try {
            if (!paymentCompleted.validate()) return;

            // view 객체 생성
            ReserveHistory reserveHistory = new ReserveHistory();
            // view 객체에 이벤트의 Value 를 set 함
            reserveHistory.setId(paymentCompleted.getId());
            reserveHistory.setUserId(paymentCompleted.getUserId());
            reserveHistory.setRoomId(paymentCompleted.getRoomId());
            reserveHistory.setReserveDt(paymentCompleted.getReserveDt());
            reserveHistory.setAmmount(paymentCompleted.getAmmount());
            reserveHistory.setPaymentMethod(
                paymentCompleted.getPaymentMethod()
            );
            reserveHistory.setReqType(paymentCompleted.getReqType());
            // view 레파지 토리에 save
            reserveHistoryRepository.save(reserveHistory);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenReserveCompleted_then_UPDATE_1(
        @Payload ReserveCompleted reserveCompleted
    ) {
        try {
            if (!reserveCompleted.validate()) return;
            // view 객체 조회
            Optional<ReserveHistory> reserveHistoryOptional = reserveHistoryRepository.findById(
                reserveCompleted.getId()
            );

            if (reserveHistoryOptional.isPresent()) {
                ReserveHistory reserveHistory = reserveHistoryOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                reserveHistory.setStatus(reserveCompleted.getStatus());
                // view 레파지 토리에 save
                reserveHistoryRepository.save(reserveHistory);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenCancleCompleted_then_UPDATE_2(
        @Payload CancleCompleted cancleCompleted
    ) {
        try {
            if (!cancleCompleted.validate()) return;
            // view 객체 조회
            Optional<ReserveHistory> reserveHistoryOptional = reserveHistoryRepository.findById(
                cancleCompleted.getId()
            );

            if (reserveHistoryOptional.isPresent()) {
                ReserveHistory reserveHistory = reserveHistoryOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                reserveHistory.setStatus(cancleCompleted.getStatus());
                // view 레파지 토리에 save
                reserveHistoryRepository.save(reserveHistory);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenRefundCompleted_then_UPDATE_3(
        @Payload RefundCompleted refundCompleted
    ) {
        try {
            if (!refundCompleted.validate()) return;
            // view 객체 조회
            Optional<ReserveHistory> reserveHistoryOptional = reserveHistoryRepository.findById(
                refundCompleted.getId()
            );

            if (reserveHistoryOptional.isPresent()) {
                ReserveHistory reserveHistory = reserveHistoryOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                reserveHistory.setCancleDt(refundCompleted.getCancleDt());
                // view 레파지 토리에 save
                reserveHistoryRepository.save(reserveHistory);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOutOfStock_then_UPDATE_4(@Payload OutOfStock outOfStock) {
        try {
            if (!outOfStock.validate()) return;
            // view 객체 조회
            Optional<ReserveHistory> reserveHistoryOptional = reserveHistoryRepository.findById(
                outOfStock.getId()
            );

            if (reserveHistoryOptional.isPresent()) {
                ReserveHistory reserveHistory = reserveHistoryOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                reserveHistory.setStatus(outOfStock.getStatus());
                reserveHistory.setQty(outOfStock.getQty());
                // view 레파지 토리에 save
                reserveHistoryRepository.save(reserveHistory);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //>>> DDD / CQRS
}
