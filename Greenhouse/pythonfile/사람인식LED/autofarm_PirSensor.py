from threading import Thread
import paho.mqtt.client as mqtt
import time
import RPi.GPIO as GPIO

GPIO.setmode(GPIO.BCM)
LED = 21
GPIO.setup(LED, GPIO.OUT, initial=GPIO.LOW)

# 쓰레드를 만들 때 run 함수를 상속해야만 한다.

class Pir(Thread):
    def __init__(self,client,value):
        super().__init__()
        self.value = ""
        self.client = client

    def run(self):
        while True:
            GPIO.setmode(GPIO.BCM)
            pirPin = 17
            GPIO.setup(pirPin, GPIO.IN)
            time.sleep(2)
            if GPIO.input(pirPin) == 1:
                self.value = 'motion detect'
                GPIO.output(LED, GPIO.HIGH)
                print("on")
            else:
                self.value = 'off'
                GPIO.output(LED, GPIO.LOW)
                print("off")


            self.client.publish("iot/pir", self.value)
            #self.client.loop(2)
