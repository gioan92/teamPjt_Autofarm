from RPi import GPIO  # 라즈베리파이 GPIO 핀을 쓰기위해 임포트
import time  # 시간 간격으로 제어하기 위해 임포트


class Servo():
    def __init__(self):
        self.pin = 16
        GPIO.setmode(GPIO.BCM)
        GPIO.setup(self.pin, GPIO.OUT)
        self.p = GPIO.PWM(self.pin, 50)
        self.p.start(0)

    def ceilopen(self):
        # 이산화탄소 수치가 올라가면 ceilopen 메서드 실행
        try:
            self.p.ChangeDutyCycle(9.5)
            time.sleep(0.5)
        except KeyboardInterrupt:
            self.p.stop()
            GPIO.cleanup()

    def ceilclose(self):
        # 이산화탄소 수치가 내려가면 ceilopen 메서드 실행
        try:
            self.p.ChangeDutyCycle(2.5)
            time.sleep(0.5)
        except KeyboardInterrupt:
            self.p.stop()
            GPIO.cleanup()

# pin = 16
# GPIO.setmode(GPIO.BCM)
# GPIO.setup(pin,GPIO.OUT)
# p = GPIO.PWM(pin,50)
# p.start(0)
# try:
#     while True:
#         p.ChangeDutyCycle(2.5)
#         time.sleep(0.5)
#         p.ChangeDutyCycle(6)
#         time.sleep(0.5)
#         p.ChangeDutyCycle(9.5)
#         time.sleep(0.5)
# except KeyboardInterrupt:
#     p.stop()
# finally:
#     GPIO.cleanup()