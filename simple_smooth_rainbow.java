#include "FastLED.h"
struct CRGB leds[120];
byte maxbright = 255;
byte max_hue = 120; //how many colors will be displayed simultaneously
byte hue_steps_inner = max_hue/8;
byte hue_steps_outer = max_hue/16;
byte hue_changer = 0;


int Fan1_in[] = {0,1,2,3,4,5,6,7}; //the first fans inner 8 LEDs
int Fan1_out[] = {8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23}; //the first fans outer 16 LEDs
int Fan2_in[] = {24,25,26,27,28,29,30,31};
int Fan2_out[] = {32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47};
int Fan3_in[] = {48,49,50,51,52,53,54,55};
int Fan3_out[] = {56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71};


void setup() {
  delay(500); // power-up safety delay
  FastLED.addLeds<NEOPIXEL, 3>(leds, 0, 24);
  FastLED.addLeds<NEOPIXEL, 5>(leds, 24, 48);
  FastLED.addLeds<NEOPIXEL, 6>(leds, 48, 72);
  FastLED.setBrightness(255);
  for(int i = 0; i < 120; i++){
      leds[i] = CRGB(0,0,0);  
  }
  FastLED.show();
}

void loop(){
  rainbow();
}

void rainbow(){
  for(int i = 0; i < 16; i++){
    leds[Fan1_in[i/2]] = CHSV(i * hue_steps_inner + hue_changer, 255, 255);
    leds[Fan1_out[i]] = CHSV(i * hue_steps_inner + hue_changer, 255, 255);
    leds[Fan2_in[i/2]] = CHSV(i * hue_steps_inner + hue_changer, 255, 255);
    leds[Fan2_out[i]] = CHSV(i * hue_steps_inner + hue_changer, 255, 255);
    leds[Fan3_in[i/2]] = CHSV(i * hue_steps_inner + hue_changer, 255, 255);
    leds[Fan3_out[i]] = CHSV(i * hue_steps_inner + hue_changer, 255, 255);
  }

  hue_changer++;
  FastLED.show();
}
