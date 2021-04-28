import serial

ser = serial.Serial('/dev/ttyAMA1', 9600, timeout=1);
while True:
        cStr = "";
        rLine = ser.readline();
        if rLine == None:
                break;
        for i in range(len(rLine)):
                cStr += chr(rLine[i]);
        print("cStr: %s" % cStr);