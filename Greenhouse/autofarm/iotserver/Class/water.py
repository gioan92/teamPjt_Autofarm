import RPi.GPIO as GPIO
import time            # 일정 시간동안 물을 공급하는게 어떨까 싶어서 넣어놨습니다.

class Water:
    def __init__(self):
        GPIO.setmode(GPIO.BCM)
        self.pin = 21; # 출력 pin번호
        GPIO.setup(self.pin, GPIO.OUT, initial = GPIO.HIGH)

    def watering(self):
        try:
            print("급여 시작")
            GPIO.output(self.pin, GPIO.LOW)
            time.sleep(5)
            print("5초 후 급여 종료");
            GPIO.output(self.pin, GPIO.HIGH)
        except KeyboardInterrupt:
            GPIO.cleanup();