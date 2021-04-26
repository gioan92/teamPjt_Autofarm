#include <SoftwareSerial.h>
SoftwareSerial RPI(2,3); // RXD=2, TXD=3

int flame = A2;
int LED = 10;

void setup() {
  Serial.begin(9600);
  RPI.begin(9600);
  
  // 불꽃감지센서 핀모드 
  pinMode(flame, INPUT);
  pinMode(LED, OUTPUT);
}

void loop() {
  // 조도센서의 값 받기 
  int cds = analogRead(A1);

  // 불꽃 감지 센서 값 받기 
   int fire = analogRead(flame);

  // 불꽃 감지 센서가 감지하면 LED 켜기 
  if(fire < 1023) {
    digitalWrite(LED, HIGH);
//    Serial.println("FIRE!!");
  }
  else{
    digitalWrite(LED, LOW);
//    Serial.println("No Fire");
  }
  
String iStr(cds);
  // 센서들의 값 보내기
  // 조도센서 + 불꽃감지센서 
  RPI.print(iStr);
  RPI.print(",");
  RPI.print(fire);
  RPI.print(";");

  // 시리얼 모니터로 확인 
  Serial.print(iStr);
  Serial.print(",");
  Serial.print(fire);
  Serial.println(";");
  delay(1000);
}
