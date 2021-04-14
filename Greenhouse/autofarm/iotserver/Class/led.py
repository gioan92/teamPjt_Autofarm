from RPi import GPIO
import time

class Led():
    def __init__(self):
        self.pin = 20;
        GPIO.setmode(GPIO.BCM)
        GPIO.setup(self.pin, GPIO.OUT, initial=GPIO.LOW)

    def lighton(self):
        try:
            GPIO.output(self.pin, GPIO.HIGH)
        except KeyboardInterrupt:
            GPIO.cleanup()
    def lightoff(self):
        try:
            GPIO.output(self.pin, GPIO.LOW)
        except KeyboardInterrupt:
            GPIO.cleanup()