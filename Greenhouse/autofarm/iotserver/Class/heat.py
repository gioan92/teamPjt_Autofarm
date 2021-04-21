from RPi import GPIO
import time

class Heat():
   def __init__(self):
       self.pin = 23;
       GPIO.setmode(GPIO.BCM)
       GPIO.setup(self.pin, GPIO.OUT, initial=GPIO.LOW)

   def heaton(self):
       try:
           GPIO.output(self.pin, GPIO.HIGH)
           print("따뜻")
           time.sleep(60)
       except KeyboardInterrupt:
           GPIO.cleanup()
   def heatoff(self):
       try:
           GPIO.output(self.pin, GPIO.LOW)
       except KeyboardInterrupt:
           GPIO.cleanup()

