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

- 데이터 저장소로부터의 읽기와 업데이트 작업을 분리하기 위해 CQRS 패턴 활용 
![image](https://github.com/user-attachments/assets/47209aa6-8bb6-46e4-a13d-a6c92ff2ccee)


## 클라우드 배포 및 단일 진입점 검증





## 테스트1. 예약 정상 처리

- http 테스트
  
![예약 요청 - 정상](https://github.com/user-attachments/assets/b9ab3b21-d205-4d0a-84ce-155b02f63296)


- 시나리오

![11 - 복사본 (2)](https://github.com/user-attachments/assets/258b91ba-1fd9-42a3-9de6-985db358f842)


- 시나리오 이벤트 호출 검증 (kafka)

![예약 - 정상](https://github.com/user-attachments/assets/7b9440b3-bed2-4aa5-ba1d-1977394888f5)

  
### 테스트2. 취소 정상 처리

- http 테스트
  
![캔슬요청](https://github.com/user-attachments/assets/354e2a2d-a281-4ab7-bfdd-a67a6abc76d6)



- 시나리오

![11 - 복사본 (2) - 복사본](https://github.com/user-attachments/assets/b74cc1fc-8e28-4aca-9966-226eaf759b83)



- 시나리오 이벤트 호출 검증 (kafka)

![캔슬 카프카](https://github.com/user-attachments/assets/26cc72ef-2d18-4410-b0c3-2ee1296cc910)

  
## 테스트3. 비즈니스 롤백 처리 - 예약중 객실 재고가 없을때 환불 이벤트 호출

- http 테스트
  
![예약 요청 - outofstock](https://github.com/user-attachments/assets/c0ee1264-e1e1-4aa7-b393-5e0faa919c2b)



- 시나리오

![11 - 복사본 (3) - 복사본](https://github.com/user-attachments/assets/9bb66917-cffd-4bd3-91cd-7dedfac86755)


- 시나리오 이벤트 호출 검증 (kafka)

  
![예약 - outofstock](https://github.com/user-attachments/assets/5823223e-6ce8-48c1-b2af-7ec52b4a28d0)


# 클라우드 네이티브 운영
## # 클라우드 배포 - Container 운영
- 빌드/배포 단계 사용 명령어
  - mvn package -B -Dmaven.test.skip=true
  - docker build -t 9rndus/reserve:0924 .
  - docker push 9rndus/reserve:0924
  - kubectl apply -f kubernetes/deployment.yml
  - kubectl apply -f kubernetes/service.yaml

- Docker hub 이미지 배포
![image](https://github.com/user-attachments/assets/8320128f-e4c3-4c1a-92ef-338eb8166219)

- AKS를 활용한 서비스 배포
![image](https://github.com/user-attachments/assets/1fafe884-c553-4015-8c54-22a577deb6e9)

## # 컨테이너 자동 확장 (HPA)
1. HPA 생성


![hpa 생성](https://github.com/user-attachments/assets/e3c5490c-ee56-47ec-8281-ff7251e3d438)

2. siege 테스트


![saga 테스트](https://github.com/user-attachments/assets/f0b17237-8fe1-4bd0-9899-1fc4c178bd67)

3. cpu 부하 확인


![cpu 부하 확인](https://github.com/user-attachments/assets/909ff0a6-9149-46af-93bb-25e32105f624)

4. pod 자동 생성 확인


![po-w](https://github.com/user-attachments/assets/5c0a148e-e348-4162-abaf-6f18f2e02d23)


## # 컨테이너로부터 환경분리 -ConfigMap

1. ConfigMap 생성

   
![image](https://github.com/user-attachments/assets/6005fff9-976f-4842-a8a4-08cd5f103765)


2. ConfigMap 참조를 위한 deployment.yaml 파일 수정


![image](https://github.com/user-attachments/assets/70b44cb5-b62f-47bd-8975-97d7d63363b0)


3. 서비스 재배포

   
![image](https://github.com/user-attachments/assets/a165a272-e3b3-4d1c-8c05-1cb76f2f7c7b)


4. 설정 확인


![image](https://github.com/user-attachments/assets/59a9bae5-80e2-432a-8801-f45387fa30a5)



## # 클라우드 스토리지 활용 - PVC

1.  PersistentVolumeClaim 생성

![image](https://github.com/user-attachments/assets/9e069181-f42d-49b4-a6f8-c77c3ef0af10)


2. deployment.yaml 파일 설정

![image](https://github.com/user-attachments/assets/74602f32-3cd1-4f9b-bf75-0fe785eaf95d)



3.  확장된 서비스에서도 test.txt가 확인되는지 검증

![image](https://github.com/user-attachments/assets/969acf18-1c69-4fe8-87f2-824913d99b42)




## # Rediness probe를 통한 무정지 배포
1. deployment.yaml 파일 readiness 설정 추가 (배포 테스트를 위해 이미지 버전 수정)


![무정지 deployment 설정](https://github.com/user-attachments/assets/8620473d-8184-46b2-94fd-5285438a661e)



2. siege 터미널을 열어서 충분한 시간동안 부하 발생


![무정지 seiga 테스트](https://github.com/user-attachments/assets/92d2588e-e4e5-4570-88cc-8ef5bc08a9f4)


3. deployment.yaml 배포


![디플로이먼트 배포](https://github.com/user-attachments/assets/e90d3918-02e2-4ac9-ae00-fbb6606deb3a)

4. seige 로그 확인


![seiga 결과](https://github.com/user-attachments/assets/0ed6ae9f-867e-4264-9f94-f09db7e26940)




