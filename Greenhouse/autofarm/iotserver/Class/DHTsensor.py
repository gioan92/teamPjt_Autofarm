import time
import Adafruit_DHT as dht11

from threading import Thread
import paho.mqtt.client as mqtt
import time
import RPi.GPIO as GPIO

from MyMessage import mymessage


class DHT(Thread):
    def __init__(self, client, value, msg):
        super().__init__()
        self.value = ""
        self.client = client
        self.message = msg;

    def run(self):
        while True:
            pin = 20
            hum, temp = dht11.read_retry(dht11.DHT11, pin)
            GPIO.setmode(GPIO.BCM)

            if hum is not None and temp is not None:
                print("1 " + str(hum) + "," + str(temp))
                self.message.content['hum'] = hum;
                self.message.content['temp'] = temp;
                print(self.message);
            else:
                print("error")

            self.value = '1 ' + str(hum) + ',' + str(temp)
            self.client.publish("iot/DHT", self.value)
            # self.client.loop(2)




