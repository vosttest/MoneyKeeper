/*************************************************************
  Download latest Blynk library here:
    https://github.com/blynkkk/blynk-library/releases/latest

  Blynk is a platform with iOS and Android apps to control
  Arduino, Raspberry Pi and the likes over the Internet.
  You can easily build graphic interfaces for all your
  projects by simply dragging and dropping widgets.

    Downloads, docs, tutorials: http://www.blynk.cc
    Sketch generator:           http://examples.blynk.cc
    Blynk community:            http://community.blynk.cc
    Follow us:                  http://www.fb.com/blynkapp
                                http://twitter.com/blynk_app

  Blynk library is licensed under MIT license
  This example code is in public domain.

 *************************************************************
  This example runs directly on NodeMCU.

  Note: This requires ESP8266 support package:
    https://github.com/esp8266/Arduino

  Please be sure to select the right NodeMCU module
  in the Tools -> Board menu!

  For advanced settings please follow ESP examples :
   - ESP8266_Standalone_Manual_IP.ino
   - ESP8266_Standalone_SmartConfig.ino
   - ESP8266_Standalone_SSL.ino

  Change WiFi ssid, pass, and Blynk auth token to run :)
  Feel free to apply it to any other example. It's simple!
 *************************************************************/

/* Comment this out to disable prints and save space */
#define BLYNK_PRINT Serial
#define ON LOW
#define OFF HIGH

#include <ESP8266WiFi.h>
#include <BlynkSimpleEsp8266.h>

// You should get Auth Token in the Blynk App.
// Go to the Project Settings (nut icon).
char auth[] = "5d99a4b1e23a4767903fa650fb344e45";

// Your WiFi credentials.
// Set password to "" for open networks.
char ssid[] = "Appoint-CWS-1-VNPT";
char pass[] = "P@SSnhucu123";

// Attach virtual serial terminal to Virtual Pin V0
WidgetTerminal terminal(V0);

// You can send commands from Terminal to your hardware. Just use
// the same Virtual Pin as your Terminal Widget
BLYNK_WRITE(V0) {
  // If you type "Marco" into Terminal Widget - it will respond: "Polo:"
  if (String("Marco") == param.asStr()) {
    terminal.println("You said: 'Marco'") ;
    terminal.println("I said: 'Polo'") ;
  }
  else {
    // Send it back
    terminal.print("Mệnh lệnh:");
    terminal.write(param.getBuffer(), param.getLength());
    terminal.println();

    if (String("mo") == param.asStr()) {
      setLights(ON);
      terminal.println("Tất cả đèn đã mở...");
    }
    else if (String("tat") == param.asStr()) {
      setLights(OFF);
      terminal.println("Tất cả đèn đã tắt...");
    }
  }

  // Ensure everything is sent
  terminal.flush();
}

BLYNK_WRITE(V1)
{
  // You'll get HIGH/1 at startTime and LOW/0 at stopTime.
  // this method will be triggered every day
  // until you remove widget or stop project or
  // clean stop/start fields of widget
  Serial.print("Got a value: ");
  Serial.println(param.asStr());

  if (String("1.0") == param.asStr()) {
    digitalWrite(D1, ON);
  }
  else{
    digitalWrite(D1, OFF);
  }
}

BLYNK_WRITE(V2)
{
  // You'll get HIGH/1 at startTime and LOW/0 at stopTime.
  // this method will be triggered every day
  // until you remove widget or stop project or
  // clean stop/start fields of widget
  Serial.print("Got a value: ");
  Serial.println(param.asStr());

  if (String("1.0") == param.asStr()) {
    digitalWrite(D2, ON);
  }
  else{
    digitalWrite(D2, OFF);
  }
}

void setLights(int state) {
  digitalWrite(D0, state);
  digitalWrite(D1, state);
  digitalWrite(D2, state);
  digitalWrite(D3, state);
  digitalWrite(D4, state);
  digitalWrite(D5, state);
  digitalWrite(D6, state);
  digitalWrite(D7, state);
}

void setOutput() {
  //    NodeMCU PIN    Blynk PIN  Relay No.
  pinMode(D0, OUTPUT); // D0      1
  pinMode(D1, OUTPUT); // D1      2
  pinMode(D2, OUTPUT); // D2      3
  pinMode(D3, OUTPUT); // D3      4
  pinMode(D4, OUTPUT); // D4      5
  pinMode(D5, OUTPUT); // D5      6
  pinMode(D6, OUTPUT); // D6      7
  pinMode(D7, OUTPUT); // D7      8

  setLights(OFF);
}

void setup()
{
  // Debug console
  Serial.begin(9600);

  Blynk.begin(auth, ssid, pass);
  // You can also specify server:
  //Blynk.begin(auth, ssid, pass, "blynk-cloud.com", 80);
  //Blynk.begin(auth, ssid, pass, IPAddress(192,168,1,100), 8080);

  // This will print Blynk Software version to the Terminal Widget when
  // your hardware gets connected to Blynk Server
  terminal.println(F("Blynk v" BLYNK_VERSION ": Device started"));
  terminal.println(F("-------------"));
  terminal.println(F("Type 'Marco' and get a reply, or type"));
  terminal.println(F("anything else and get it printed back."));
  terminal.flush();  

  setOutput();
}

void loop()
{
  Blynk.run();
}
