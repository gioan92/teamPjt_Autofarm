import serial
from threading import Thread
import time
import RPi.GPIO as GPIO

ser = serial.Serial('/dev/ttyAMA1', 115200, timeout=1);



class Arduino(Thread) :
    def __init__(self, client, value):
        super().__init__()
        self.value = ""
        self.client = client

    def run(self):
        while True:
            cStr = "";
            rLine = ser.readline();


            check = 0;

            if rLine is not None :
                for i in range(len(rLine)):
                    cStr += chr(rLine[i]);
                try :
                    iStr = str(cStr);
                except:
                    pass;
                istr = str(iStr);

            else :
                print("error")

            fstr = istr.split(";")
            print("2 %s" % fstr[0]);

            self.value = '2 ' + fstr[0]
            self.client.publish("iot/ARD", self.value)













