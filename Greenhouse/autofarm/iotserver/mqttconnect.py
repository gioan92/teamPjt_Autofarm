import paho.mqtt.client as mqtt
from led import Led
from servo import Servo
from water import Water
from pir import Pir
from heat import Heat

import RPi.GPIO as GPIO
import DHTsensor
import ArduinoSensorUART
import time

light = None
ceil = None
water = None
pir = None
heat = None

sensors = [
    # Sensor(5, (3, 10), 'iot/user1/temp'),
    # Sensor(7, (20, 60), 'iot/user1/humi'),
    # Sensor(10, (20, 80), 'iot/user1/illu'),
    # Sensor(12, (0, 1), 'iot/user1/dust'),
]
led = 21

def on_connect(client,usedata,flags,rc):
    global light, ceil, water, pir
    print("connect.."+str(rc))
    if rc == 0:
        client.subscribe("mydata/greenhouse") # mydata/led - 토픽명
    else:
        print("연결실패")

# 메세지가 도착했을 때 처리할 일들 - 여러가지 장비 제어, Mongodb에 저장
def on_message(client,userdata,msg):
    GPIO.setmode(GPIO.BCM)
    global light, ceil, water, pir
    myval = msg.payload.decode("utf-8")
    print("메세지도착 " + str(myval))
    if str(myval) == 'light_up':
        light.lighton()
    elif str(myval) == 'light_down':
        light.lightoff()
    elif str(myval) == 'ceil_open':
        ceil.ceilopen()
    elif str(myval) == 'ceil_close':
        ceil.ceilclose()
    elif str(myval) == 'water_on':
        print("Pub(Android)에서 water_on 버튼이 눌려졌습니다.")
        water.watering()



mqttClient = mqtt.Client()
mqttClient.on_connect = on_connect
mqttClient.on_message = on_message
mqttClient.connect("192.168.200.115", 1883, 60)

light = Led()
ceil = Servo()
water = Water()
pir = Pir(mqttClient, "")
pir.start()
heat = Heat()
heat.heaton()
dht = DHTsensor.DHT(mqttClient, "")
dht.start()
ard = ArduinoSensorUART.Arduino(mqttClient, "")
ard.start()

mqttClient.loop_forever()


