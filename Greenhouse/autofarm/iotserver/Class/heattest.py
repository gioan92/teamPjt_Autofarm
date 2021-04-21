import RPi.GPIO as GPIO
import time

# GPIO 핀 모드를 설정 BCM : 핀 번호를 BCM 모드, BOARD로 정의하면 물리적인 핀번호
GPIO.setmode(GPIO.BCM)
LED = 23
LED2 = 24

# LED로 정의한 21번을 GPIO의 output핀으로 쓰겠다고 정의.
GPIO.setup(LED, GPIO.OUT, initial = GPIO.LOW)
GPIO.setup(LED2, GPIO.OUT, initial = GPIO.LOW)
GPIO.output(LED, GPIO.HIGH) # 21번으로 출력
GPIO.output(LED2, GPIO.HIGH) # 21번으로 출력