# 예제 - 호텔 예약

## 기능적 요구사항

- 예약 정상 처리
  - 고객이 객실을 선택하여 예약 요청한다.
  - 고객이 결제한다.
  - 결제가 완료되면 호텔에 예약 내용이 전송된다.
  - 객실이 있다면 예약처리가 완료되며 객실 상태를 업데이트한다.
   
- 취소 정상 처리
  - 고객이 마이페이지를 조회한다.
  - 고객이 취소 요청한다.
  - 결제내역을 환불한다.
  - 환불이 완료되면 호텔에 환불 내용이 전송된다.
  - 취소처리가 완료되며 객실 상태를 업데이트한다.
  
- 예약중 객실 재고가 없을때 비즈니스 롤백 보상처리
  - 고객이 객실을 선택하여 예약 요청한다.
  - 고객이 결제한다.
  - 남은 객실이 없다면 결제 환불 단계로 비즈니스 롤백하여 결제 내역을 환불한다.
  - 취소처리가 완료되며 객실 상태를 업데이트한다.


## Event Storming 결과

![image](https://github.com/user-attachments/assets/330f12bb-c1d6-475e-9837-87cf97e5d668)

## 클라우드 배포 및 단일 진입점 검증

![image](https://github.com/user-attachments/assets/1fafe884-c553-4015-8c54-22a577deb6e9)




## 테스트1. 예약 정상 처리

- http 테스트
  
![예약 요청 - 정상](https://github.com/user-attachments/assets/b9ab3b21-d205-4d0a-84ce-155b02f63296)


- 시나리오



- 시나리오 이벤트 호출 검증 (kafka)

![예약 - 정상](https://github.com/user-attachments/assets/7b9440b3-bed2-4aa5-ba1d-1977394888f5)

  
### 테스트2. 취소 정상 처리

- http 테스트
  
![캔슬요청](https://github.com/user-attachments/assets/64e68f5c-1588-403d-bd69-df5d988b2c8c)


- 시나리오



- 시나리오 이벤트 호출 검증 (kafka)

![캔슬 카프카](https://github.com/user-attachments/assets/26cc72ef-2d18-4410-b0c3-2ee1296cc910)

  
## 테스트3. 예약중 객실 재고가 없을때 비즈니스 롤백 보상처리

- http 테스트
  
![예약 요청 - outofstock](https://github.com/user-attachments/assets/c0ee1264-e1e1-4aa7-b393-5e0faa919c2b)



- 시나리오



- 시나리오 이벤트 호출 검증 (kafka)

  
![예약 - outofstock](https://github.com/user-attachments/assets/5823223e-6ce8-48c1-b2af-7ec52b4a28d0)


