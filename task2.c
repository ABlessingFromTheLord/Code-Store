#include "task2.h"
#include "msp.h"

const struct State {
    uint8_t ledOut; // 1-bit output (0 LED OFF, 1 LED ON)
    uint32_t Next[4]; // list of next states
};
typedef const struct State State_t;
#define locked 0
#define one 1
#define two 2
#define unlocked 3
#define BUTTON1 BIT1
#define BUTTON2 BIT4


State_t FSM_INT[4] = {
                          {0, {one, locked}},
                          {0, {locked, two}},
                          {0, {unlocked, locked}},
                          {1, {unlocked, unlocked}} //these are the transitions

};

// Global variable required for interrupt based computation
static uint32_t currentState_int = locked;

    uint8_t changed = 0;
    uint8_t hold1 = 0;
    uint8_t hold2 = 0;
    uint8_t temp1 = 0;
    uint8_t temp2 = 0;


void PORT1_IRQHandler() {
    asm("   cpsid i"); // DO NOT TOUCH

                  uint32_t delay = 2000000;       //2000000 = ca. 6s
                  changed = 0;
                  while(delay !=0){
                      temp1 = checkS1();
                      temp2 = checkS2();
                      if(temp1 && hold1 == 0){
                          changed = 1;
                          hold1 = 1;
                          break;
                      }

                      else if (temp1 == 0 && hold1 == 1){
                          currentState_int = FSM_INT[currentState_int].Next[0];
                          changed = 1;
                          hold1 = 0;
                          led1Out(FSM_INT[currentState_int].ledOut);
                          break;

                      }

                      if(temp2 && hold2 == 0){
                          changed = 1;
                          hold2 = 1;
                          break;

                      }
                      else if (temp2 == 0 && hold2 == 1){
                          currentState_int = FSM_INT[currentState_int].Next[1];
                          changed = 1;
                          led1Out(FSM_INT[currentState_int].ledOut);
                          hold2 = 0;
                          break;
                      }
                      delay--;

                  }
                  if(changed == 0 && currentState_int!=unlocked) currentState_int = 0;

              led1Out(FSM_INT[currentState_int].ledOut);

              uint8_t* buttons = 0x40004C1C;
              *buttons &= ~0x12;


    asm("   cpsie i"); // DO NOT TOUCH
}


void fsm_interrupt() {
    // INIT ALL SUBSYSTEMS
    led1Init();
    buttonInit_Interrupt();

    // INITIAL OUTPUT
    led1Out(FSM_INT[currentState_int].ledOut);

    // ACTUAL COMPUTATION THAT WILL BE INTERRUPTED BY YOUR FSM IRQ
    volatile uint32_t very_important_task = 0;
    while(1) {
        very_important_task++;
        for(uint32_t i = 0; i < 327000; i++) {
            ;
        }
    }
}


//2c
/* The function of cpsid i is to disable all interrupts and cpsie i is to enable all interruts.
 *
 * If you removed the "cpisd i" instruction at the beginning of the interrupt handler, other
 * interrupts could occur while the handler is executing, and thus be missed and not be
 *  triggered.

    If you removed the "cpise i" instruction at the end of the interrupt handler, the interrupts
    would remain disabled even after the handler has finished executing, which could prevent other
    interrupt handlers from running and cause the system to behave unpredictably.
 * */
