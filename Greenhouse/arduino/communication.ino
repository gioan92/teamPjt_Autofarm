#include <SoftwareSerial.h>
SoftwareSerial RPI(2,3); // RXD=2, TXD=3

int flame = A0;
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
  Serial.print("flame_sensor : ");
  Serial.println(fire);

  if(fire < 1023) {
    digitalWrite(LED, HIGH);
    Serial.println("FIRE!!");
  }
  else{
    digitalWrite(LED, LOW);
    Serial.println("No Fire");
  }
  

  // 조도센서 값 보내기 
  RPI.print(cds);
  Serial.println(cds);
  Serial.println("1초 기다립니다.");
  delay(3500);
}
