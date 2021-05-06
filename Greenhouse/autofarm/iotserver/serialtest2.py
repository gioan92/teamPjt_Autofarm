from pyfirmata import  Arduino ,util
import RPi.GPIO as GPIO
import time

board = Arduino("/dev/ttyACM0")
pinA0 = board.get_pin("a:0:i")

it = util.Iterator(board)
it.start()
pinA0.enable_reporting()
try:
    while True:
        val = pinA0.read()
        print("센서값: "+ val)
        time.sleep(1)

except KeyboardInterrupt:
    GPIO.cleanup()

