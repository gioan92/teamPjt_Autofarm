from serial import Serial

#ser = Serial('COM8', 9600)  # 윈도우
ser = Serial('dev/ttyACM0',115200) #라즈베리파이

while True:
    if ser.readable():
        res = ser.readline()
        print(res.decode()[:len(res) - 1])
