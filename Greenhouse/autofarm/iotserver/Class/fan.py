from RPi import GPIO
import time

class Fan():
   def __init__(self):
       self.pin = 27;
       GPIO.setmode(GPIO.BCM)
       GPIO.setup(self.pin, GPIO.OUT, initial=GPIO.LOW)

   def fanon(self):
       try:
           print("fanon")
           GPIO.output(self.pin, GPIO.HIGH)
           time.sleep(10)
       except KeyboardInterrupt:
           GPIO.cleanup()
   def fanoff(self):
       try:
           print("fanof f")
           GPIO.output(self.pin, GPIO.LOW)
           time.sleep(10)
       except KeyboardInterrupt:
           GPIO.cleanup()

