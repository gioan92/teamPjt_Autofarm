import serial
from threading import Thread
import time
import RPi.GPIO as GPIO

ser = serial.Serial('/dev/ttyAMA1', 9600, timeout=1);


class CDS(Thread) :
    def __init__(self, client, value):
        super().__init__()
        self.value = ""
        self.client = client

    def run(self):
        while True:
            cStr = ""
            rLine = ser.readline();
            if rLine != None:
                for i in range(len(rLine)):
                    cStr += chr(rLine[i]);
                rLine = None;
                break;

        while True:
            time.sleep(3)
            self.value = cStr
            self.client.publish("iot/CDS", self.value)
            print(cStr)
            # self.client.loop(2)




