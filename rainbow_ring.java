//================================================
#include "FastLED.h"
#define BRIGHTNESS  255      // 255 is full brightness, 128 is half
struct CRGB l2[120];
//================================================

byte idex = 0;
byte sped = 10;
byte maxbright = 255;
int animation = 0;
int hue = 0;

int ring[] = {23,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,8};
int intern[] = {7,0,1,2,3,4,5,6,7,0};

int ring2[] = {33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,32,33,34};
int intern2[] = {24,25,26,27,28,29,30,30};

int ring3[] = {59,60,61,62,63,64,65,66,67,68,69,70,71,56,57,58,59,60};
int intern3[] = {55,48,49,50,51,52,53,54,55,48};

int ring4[] = {85,86,87,88,89,90,91,92,93,94,95,80,81,82,83,84,85,86};
int intern4[] = {79,72,73,74,75,76,77,78,79,72};

int ring5[] = {111,112,113,114,115,116,117,118,119,104,105,106,107,108,109,110,111,112};
int intern5[] = {103,96,97,98,99,100,101,102,103,96};

void setup() {
  delay( 1000 ); // power-up safety delay
  FastLED.addLeds<NEOPIXEL, 3>(l2, 0, 24);
  FastLED.addLeds<NEOPIXEL, 5>(l2, 24, 48);
  FastLED.addLeds<NEOPIXEL, 6>(l2, 48, 72);
  FastLED.addLeds<NEOPIXEL, 9>(l2, 72, 96);
  FastLED.addLeds<NEOPIXEL, 10>(l2, 96, 120);
  FastLED.setBrightness( BRIGHTNESS );
}

void loop(){
rainbowRing(l2);
}

void rainbowRing(CRGB leds[]){

    for(int i = 0; i < 120; i++){
      leds[i] = CRGB(0,0,0);  
    }
    
    for(int i = 0; i <= maxbright; i+=sped){ //thats incredible inefficient. >=3 Fans and the animation speed will significantly decrease
      int a = hue-12;
      int b = hue+(25-i/5)/2;
      int c = hue+12;   
      leds[ring[idex-1]] = CHSV(a,255,maxbright-i);
      leds[ring[idex]] = CHSV(b,255,maxbright);
      leds[ring[idex+1]] = CHSV(c,255,i);

      leds[ring2[idex-1]] = CHSV(20+a,255,maxbright-i);
      leds[ring2[idex]] = CHSV(20+b,255,maxbright);
      leds[ring2[idex+1]] = CHSV(20+c,255,i);

      leds[ring3[idex-1]] = CHSV(40+a,255,maxbright-i);
      leds[ring3[idex]] = CHSV(40+b,255,maxbright);
      leds[ring3[idex+1]] = CHSV(40+c,255,i);

      leds[ring4[idex-1]] = CHSV(60+a,255,maxbright-i);
      leds[ring4[idex]] = CHSV(60+b,255,maxbright);
      leds[ring4[idex+1]] = CHSV(60+c,255,i);

      leds[ring5[idex-1]] = CHSV(80+a,255,maxbright-i);
      leds[ring5[idex]] = CHSV(80+b,255,maxbright);
      leds[ring5[idex+1]] = CHSV(80+c,255,i);

      FastLED.show();
    }

    if(animation < 70){ //70 is the max speed, change like you want it
      sped++;
      animation++;  
    }else{
      sped--;
    }
    if(sped == 20){animation = 0;} //20 is the min speed, again you can change that
    //for only one fan I would use 6-80

    idex++;
    Serial.println(hue);
    hue+=5;
    if(hue == 267){hue=12;}
    if ( idex > 16) {
        idex = 1; 
    }
}
