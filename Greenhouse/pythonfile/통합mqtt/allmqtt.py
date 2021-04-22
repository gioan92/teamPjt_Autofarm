import paho.mqtt.client as mqtt
import RPi.GPIO as GPIO
import DHTsensor
import cdsuart

sensors = [
    # Sensor(5, (3, 10), 'iot/user1/temp'),
    # Sensor(7, (20, 60), 'iot/user1/humi'),
    # Sensor(10, (20, 80), 'iot/user1/illu'),
    # Sensor(12, (0, 1), 'iot/user1/dust'),
]
led = 21


class MyMqtt_Sub():
    def __init__(self):
        client = mqtt.Client()
        client.on_connect = self.on_connect
        client.on_message = self.on_message
        client.connect("192.168.0.187", 1883, 60)
        dht = DHTsensor.DHT(client, "")
        dht.start()
        cds = cdsuart.CDS(client, "")
        cds.start()
        client.loop_forever()

    def on_connect(self, client, userdata, flags, rc):
        print("connect.." + str(rc))
        if rc == 0:
            client.subscribe("mydata/led")
        else:
            print("연결실패")

    def on_message(self, client, userdata, msg):
        GPIO.setmode(GPIO.BCM)
        GPIO.setup(led, GPIO.OUT, initial=GPIO.LOW)
        myval = msg.payload.decode("utf-8")
        print(myval)
        print(msg.topic + "----" + str(myval))
        if myval == "led_on":
            print("test")
            GPIO.output(led, GPIO.HIGH)
        else:
            GPIO.output(led, GPIO.LOW)
            # GPIO.cleanup()


if __name__ == "__main__":
    try:
        mymqtt = MyMqtt_Sub()
    except KeyboardInterrupt:
        print("종료")
        GPIO.cleanup()



