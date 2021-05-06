import paho.mqtt.client as mqtt
from led import Led
from servo import Servo
from water import Water
from pir import Pir
from heat import Heat
from fan import Fan

import RPi.GPIO as GPIO
import DHTsensor
import ArduinoSensorUART
import time

from flask import Flask, render_template, request, Response, jsonify

from MyMessage import mymessage

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
        water.watering()
    elif str(myval) == 'heat_on':
        heat.heaton()
    elif str(myval) == 'fan_on':
        fan.fanon()


app = Flask(__name__);

@app.route("/upload")
def upload():
    global dht;
    content = dht.message.content;
    return jsonify(content);

if __name__ == "__main__":
    mqttClient = mqtt.Client()
    mqttClient.on_connect = on_connect
    mqttClient.on_message = on_message
    mqttClient.connect("192.168.99.209", 1883, 60)

    msg = mymessage(0, 0);
    dht = DHTsensor.DHT(mqttClient, "", msg)
    dht.start()

    light = Led()
    ceil = Servo()
    water = Water()
    pir = Pir(mqttClient, "")
    pir.start()
    heat = Heat()
    fan = Fan()

    ard = ArduinoSensorUART.Arduino(mqttClient, "")
    ard.start()

    app.run(host="0.0.0.0", debug=True, threaded=True);

    mqttClient.loop_forever()


