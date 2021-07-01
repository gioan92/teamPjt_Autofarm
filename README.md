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
  
  ③ 단말기 안드로이드 어플을 이용하여 농장 시스템 원격 수동 제어
  
  ④ 작물 운송 트랙터 라인 트레이싱을 통한 자율 주행
  
- 기대효과 : 

  - 농장 통합 관리 솔루션으로, 비닐하우스의 환경을 실시간으로 모니터링하고 환경을 자동으로 제어해 농작물이 잘 자랄 수 있는 환경을 조성
  - 각종 센서와 출력 모듈을 활용한 농장 모니터링 및 제어 자동화, 자율주행 트랙터를 활용한 수확 및 운송 과정 자동화를 통해 인건 비용 및  노동 시간 절감



---



### Tech Stack

- Desktop
  - Pycharm 20.3.1
  - Anaconda3 / Python 3.7.6
  - Django 3.1.5
  - Android Studio 4.1.1
  - Mosquitto 1.6.9
  - MariaDB 15.1
  - MySql
  - NoSql (MongoDB)
- Arduino Uno
  - 라인트레이싱(적외선센서)
  - FSR402 압력센서
  - LM35 온도센서
  - CDS 조도센서
  - 펌프모터
  - L293D 모터쉴드
    - Fan
    - 발열패드
- RaspberryPi4
  - LED
  - DHT 온습도센서
  - 움직임감지센서
  - 서보모터
  - L-51ROPT1D2 불꽃감지센서



---



### Demonstration

* 발표자료

   [프로젝트 결과보고서_2조.pptx](..\..\project_autofarm\프로젝트 결과보고서_2조.pptx) 

* 시연영상

<video src="C:\Users\USER\Desktop\project_autofarm\동영상모음\1펌프모터수동제어,천장모터수동제어,LED수동제어,움직임감지센서LED.mp4"style="width:40%; height:40%"></video>

<video src="C:\Users\USER\Desktop\project_autofarm\동영상모음\2_33도기준쿨링팬,열선자동제어.mp4" style="width:40%; height:40%"></video>

<video src="C:\Users\USER\Desktop\project_autofarm\동영상모음\3습도물펌프자동제어.mp4"style="width:40%; height:40%"></video>

<video src="C:\Users\USER\Desktop\project_autofarm\동영상모음\4불꽃감지센서알림.mp4"style="width:30%; height:30%"></video>

<video src="C:\Users\USER\Desktop\project_autofarm\동영상모음\5트랙터화면제어,라인트레이서.mp4"style="width:30%; height:30%"></video>