import paho.mqtt.client as mqtt
from led import Led
from servo import Servo

light = None
ceil = None

def on_connect(client,usedata,flags,rc):
    global light, ceil
    print("connect.."+str(rc))
    if rc == 0:
        client.subscribe("mydata/greenhouse") # mydata/led - 토픽명

    else:
        print("연결실패")

# 메세지가 도착했을 때 처리할 일들 - 여러가지 장비 제어, Mongodb에 저장
def on_message(client,userdata,msg):
    global light, ceil
    myval = msg.payload.decode("utf-8")
    print("메세지도착" + str(myval))
    if str(myval) == 'light_up':
        light.lighton()
    elif str(myval) == 'light_down':
        light.lightoff()
    elif str(myval) == 'ceil_open':
        ceil.ceilopen()
    elif str(myval) == 'ceil_close':
        ceil.ceilclose()


# 객체생성
mqttClient = mqtt.Client()
light = Led()
ceil = Servo()
# 브로커에 연결이 되면 내가 정의해 놓은 on_connect라는 함수가 실행되도록 등록
mqttClient.on_connect = on_connect   # 연결되면 on_connect를 실행
# 브로커에서 메세지가 전달되면 내가 등록해 놓은 on_message함수가 실행
mqttClient.on_message = on_message   # 메세지가 오면 on_message 실행
# 브로커에 연결하기
mqttClient.connect("192.168.0.197",1883,60)
# 토픽이 전달될 때 까지 수신대기기
mqttClient.loop_forever( )

