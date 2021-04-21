
#include <AFMotor.h>

AF_DCMotor motor_fan(1);
AF_DCMotor motor_heat(3);
float temperature;  
int reading;  
int lm35Pin = A0;
int led = 13;

int i;
void setup() {
  Serial.begin(9600);
  analogReference(INTERNAL);
  motor_heat.setSpeed(150);
  motor_heat.run(RELEASE);
  motor_fan.setSpeed(200);
  motor_fan.run(RELEASE);
  pinMode(led, OUTPUT);
}

void loop() {
  reading = analogRead(lm35Pin);
  temperature = reading / 9.31;
  Serial.println(temperature);
  if (temperature < 33){
    digitalWrite(led, HIGH);
    heat_on();
    fan_off();
  }else{
    digitalWrite(led, LOW);
    fan_on();
    heat_off();
  }
  delay(1000);
}

void heat_on() {
  motor_heat.run(FORWARD);
  motor_heat.run(FORWARD);
}
void heat_off() {
  motor_heat.run(RELEASE);
  motor_heat.run(RELEASE);
}
void fan_on() {
  motor_fan.run(FORWARD);
  for (i=0; i<255; i++) {
    motor_fan.setSpeed(i);  
    delay(3);
  }
  
  for (i=255; i!=0; i--) {
    motor_fan.setSpeed(i);  
    delay(3);
  }
  motor_fan.run(BACKWARD);
  for (i=0; i<255; i++) {
    motor_fan.setSpeed(i);  
    delay(3);
  }
  delay(1000);
  for (i=255; i!=0; i--) {
    motor_fan.setSpeed(i);  
    delay(3);
  }
  delay(1000);
}
void fan_off() {
  motor_fan.run(RELEASE);
}
