const int leftForward = 11;
const int leftBackward = 12;
const int rightForward = 10;
const int rightBackward = 9;

const int echoPinForwardSensor = 3;
const int trigPinForwardSensor = 2;
const int echoPinLeftSensor = 4;
const int trigPinLeftSensor = 5;

int fastSpeed = 205, balanceValue = 50;
int distanceForwardSensor, distanceLeftSensor, durationLeftSensor, durationForwardSensor, distanceWhenStops = 45, distanceToGoBackwards = 25;

bool stopped = false;

unsigned long durationToTurn = 1200;

void setup () 
{
  pinMode(leftForward, OUTPUT);
  pinMode(leftBackward, OUTPUT);
  pinMode(rightForward, OUTPUT);
  pinMode(rightBackward, OUTPUT);

  pinMode(trigPinForwardSensor, OUTPUT);
  pinMode(echoPinForwardSensor, INPUT);
  pinMode(trigPinLeftSensor, OUTPUT);
  pinMode(echoPinLeftSensor, INPUT);

  Serial.begin(9600);
}

void loop () 
{
  //Calculeaza distanta pentru senzorul din fata
  digitalWrite(trigPinForwardSensor, LOW);
  delayMicroseconds(2);
  
  digitalWrite(trigPinForwardSensor, HIGH);
  delayMicroseconds(10);
  digitalWrite(trigPinForwardSensor, LOW);

  durationForwardSensor = pulseIn(echoPinForwardSensor, HIGH);
  distanceForwardSensor = durationForwardSensor * 0.034 / 2; 

  //Calculeaza distanta pentru senzorul din stanga
  digitalWrite(trigPinLeftSensor, LOW);
  delayMicroseconds(2);

  digitalWrite(trigPinLeftSensor, HIGH);
  delayMicroseconds(10);
  digitalWrite(trigPinLeftSensor, LOW);

  durationLeftSensor = pulseIn(echoPinLeftSensor, HIGH);
  distanceLeftSensor = durationLeftSensor * 0.034 / 2;

  if (distanceForwardSensor <= distanceToGoBackwards && distanceForwardSensor > 0)
  {
    goBackwards();
  }
  else if (distanceForwardSensor > distanceWhenStops) 
  {
    goForward();
  }
  else if (distanceForwardSensor <= distanceWhenStops && stopped == false)
  {
    stopCar();
    delay(300);
    stopped = true;
  }
  if (stopped ==  true)
  {
    if (distanceLeftSensor <= distanceWhenStops && distanceLeftSensor > 0)
    {
      //rotim la dreapta daca am la stanga
      for (unsigned long timeStart = millis(); (millis() - timeStart) < durationToTurn; )
      {
        turnRight();  
      }
    }
    else
    {
      //si invers
      for (unsigned long timeStart = millis(); (millis() - timeStart) < durationToTurn; )
      {
        turnLeft();  
      }
    }
    
    stopped = false;
  }
}

void goForward ()
{
  analogWrite(leftForward, 90);
  digitalWrite(leftBackward, LOW);
  analogWrite(rightForward, 90);
  digitalWrite(rightBackward, LOW);
}

void stopCar ()
{
  digitalWrite(leftForward, LOW);
  digitalWrite(leftBackward, LOW);
  digitalWrite(rightForward, LOW);
  digitalWrite(rightBackward, LOW);
}

void turnLeft ()
{
  digitalWrite(leftForward, LOW);
  digitalWrite(leftBackward, LOW);
  analogWrite(rightForward, 100);
  digitalWrite(rightBackward, LOW);
}
void turnRight ()
{
  analogWrite(leftForward, 100);
  digitalWrite(leftBackward, LOW);
  digitalWrite(rightForward, LOW);
  digitalWrite(rightBackward, LOW);
}

void goBackwards ()
{
  digitalWrite(leftForward, LOW);
  analogWrite(leftBackward, 90);
  digitalWrite(rightForward, LOW);
  analogWrite(rightBackward, 90);
}
