시나리오만으로 Checklist 점검하기가 쉽지 않은것 같아서 EventStorming Model을 퀵하게 그려보았습니다. 


# 서비스 시나리오

공유 자전거 서비스 - https://oliviabbase.tistory.com/114

## 공유자전거(gBike) 시나리오
1. 사용자가 회원가입(signIn) 한다. (결제정보 포함)
2. 사용자가 서비스를 이용해 자전거를 탐색한다. (위치, 가용한 자전거 수 등...)
   == Offline으로 자전거가 있는 곳으로 이동 ==
3. 사용자가 자전거를 선택하여 대여신청(requestRent)을 한다.  
4. 자전거가 사용가능할 경우 대여신청이 승인된다. (validateAndRent)  <--- 이걸 쪼개야 할지.. 일단 복잡해지니까 한통으로..
5. 사용자는 자전거 사용 완료 후 반납한다.(Return)
6. 반납이 되면 시스템은 이용요금을 계산(calculateFee)하여 사용자 서비스에 알린다.(notifyBill)
7. 사용자는 계산된 요금을 확인하고 결제(pay)한다.
8. 자전거 이용이 종료된다. 
9. 이용이 종료된 자전거는 자전거 관리자가 충전하고, 충전이 되면 상태가 업데이트된다.  

## 비기능적 요구사항

1. 트랜잭션
  - QR코드 스캔 시 자전거 사용가능 상태를 받아 사용가능 상태 일 때만 이용 가능 하고, 즉시 자전거는 대여되어야 한다. (sync 호출)
3. 장애격리
  - 결제 기능이 정상이 아니더라도 자전거 반납은 항상 수행되어야 하며 기능 정상화 후 추후 결제처리 될 수 있도록 한다.
    (Async, event-driven, Eventual consistency)
  - 대여요청이 과중되면 호출을 잠시 막고 잠시 후에 하도록 유도한다. (circuit breaker, fallback)
3. 성능
  - 사용자가 자전거 위치를 항상 빠르게 확인 할 수 있는 화면을 제공해야 한다. (CQRS)
4. 운영
  - 운영 상 서비스 Health상태를 보장해야 한다. (Livenness Probe, Readiness Probe)

## 모델  
http://www.msaez.io/#/storming/mD3qVoL8dKTd723pyildzLwnvQq2/mine/df92c4ec76d72a5d065887aca5cf03b5

모델은 마음껏 수정하셔도 됩니다!!

Payment가 third-party일 경우, BoundedContext 간 크기가 균등하지 않아서, Billing(과금)을 추가해 봤습니다. 
가격정책은 지역, 날씨, 시간, promotion 상황에 따라 달라질 가능성이 있으므로 UserService와 분리.
자전거 관리는 외주를 주어, 충전/고장수리 등을 통해 자전거 상태를 관리하는 것을 가정했습니다. (모델에는 충전만 표현)


![image](https://user-images.githubusercontent.com/36217195/119628825-6a94ee00-be48-11eb-88ac-50f56d2e59d3.png)



