IoT A반 2조 프로젝트

## README

[1. Title](#title)

[2. Outline](#outline)

[3. Tech Stack](#tech-stack)

[4. DB](#DB)

[5. Demonstration](#demonstration)



---



### Title

- Auto Farm (스마트 농장 구현)



---



### Outline

- 주제 : 농장 자동 제어 시스템
- 내용 :
  ① 농작물 재배를 위한 센서(온습도, 채광, CO2) 모니터링
  ② 자동 수분 급여, 창문 개폐, 발열패드·쿨링팬을 통한 농장 내 온습도, 채광, CO2 조절
  ② 단말기 안드로이드 어플을 이용하여 농장 시스템 원격 수동 제어
  ③ 작물 운송 트랙터 라인 트레이싱을 통한 자율 주행
- 기대효과 : 농장 자동 제어를 위한 통합 솔루션으로, 작물이 잘 자라기 위한 환경 자동 조절 및 자율주행하는 트랙터를 이용하여 수확까지 



---



### Tech Stack

- Desktop
  - Pycharm 20.3.1
  - Anaconda3 / Python 3.7.6
  - Mosquitto 1.6.9
  - MariaDB 15.1
  - MySql
  - NoSql (MongoDB)
- Arduino Uno
  - 라인트레이싱(적외선센서)
  - FSR402 압력센서
  - L293D 모터쉴드
    - Fan
    - 발열패드
- RaspberryPi4
  - LED
  - DHT 온습도센서
  - 움직임감지센서
  - 불꽃감지센서
  - CO2센서



---



### Demonstration

