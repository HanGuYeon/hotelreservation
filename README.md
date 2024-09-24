# 예제 - 호텔 예약

### 기능적 요구사항

1. 고객이 객실을 선택하여 예약 요청한다.
2. 고객이 결제한다.
3. 결제가 완료되면 호텔에 예약 내용이 전송된다.
4. 객실이 있다면 예약처리가 완료되며 객실 상태를 업데이트한다.
5. 남은 객실이 없다면 결제 환불 단계로 비즈니스 롤백하여 환불 완료 후 객실 상태를 업데이트한다.
6. 고객이 마이페이지를 조회한다.
7. 고객이 예약내역을 취소할 수 있다.


### Event Storming 결과

![image](https://github.com/user-attachments/assets/330f12bb-c1d6-475e-9837-87cf97e5d668)

### 시나리오1. 예약 정상 처리

  
### 시나리오2. 취소 정상 처리

  
### 시나리오3. 예약중 객실 재고가 없을때 비즈니스 롤백 보상처리

- 테스트
  
![예약 요청 - outofstock](https://github.com/user-attachments/assets/d0895c1d-0db9-4e23-914c-e4656e0a3002)


- 이벤트 검증 (kafka)
  
![예약 - outofstock](https://github.com/user-attachments/assets/5823223e-6ce8-48c1-b2af-7ec52b4a28d0)



- -> 동그라미
  - -> 동그라미 두번째 줄바꿈 


## 
