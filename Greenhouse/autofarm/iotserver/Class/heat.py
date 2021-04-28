from RPi import GPIO
import time

class Heat():
   def __init__(self):
       self.pin = 23;
       self.ledpin = 26;
       GPIO.setmode(GPIO.BCM)
       GPIO.setup(self.pin, GPIO.OUT, initial=GPIO.LOW)
       GPIO.setup(self.ledpin, GPIO.OUT, initial=GPIO.LOW)

   def heaton(self):
       try:
           print("heaton")
           GPIO.output(self.pin, GPIO.HIGH)
           GPIO.output(self.ledpin, GPIO.HIGH)
           time.sleep(30)
       except KeyboardInterrupt:
           GPIO.cleanup()
   def heatoff(self):
       try:
           print("heatoff")
           GPIO.output(self.pin, GPIO.LOW)
           GPIO.output(self.ledpin, GPIO.LOW)
           time.sleep(30)
       except KeyboardInterrupt:
           GPIO.cleanup()

