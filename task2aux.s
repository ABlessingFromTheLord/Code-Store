	.text
	.thumb
	.align 2
	.global buttonInit_Interrupt

buttonInit_Interrupt: .asmfunc
	ldr r1, ioBase
	;initializing buttons 1 and 4 to pins 1 and 4

	;setting P1SEL0 to 0
	ldrb r2, [r1, #0xA]
	mvn r3, #18 ;preparing the bitmask
	and r2, r3
	strb r2, [r1, #0xA]

	;setting P1SEL1 to 0
	ldrb r2, [r1, #0xC]
	and r2, r3
	strb r2, [r1, #0xC]

	;setting P1DIR to 0
	ldrb r2, [r1, #0x4]
	and r2, r3
	strb r2, [r1, #0x4]

	;setting P1REN to 1
	mov r3, #18 ;preparing the bitmask
	ldrb r2, [r1, #0x6]
	orr r2, r3
	strb r2, [r1, #0x6]

	;setting P1OUT TO 1
	ldrb r2, [r1, #0x2]
	orr r2, r3
	strb r2, [r1, #0x2]

	;setting P1IES to 1
	ldrb r2, [r1, #0x18]
	orr r1, r2
	strb r1, [r2, #0x18]

	;setting P1IE to 1
	ldrb r2, [r1, #0x1A]
	orr r1, r2
	strb r2, [r1, #0x1A]

	;Enable Interrupt at bumper port (port4) (number 38)
	ldr r0, NVICBase
	ldr r1, [r0, #0x104]		;Enable Interrupt at bumper port (port4) (number 38)
	mov r2, #1
	lsl r2, #3
	orr r1, r2
	str r1, [r0, #0x104]

	bx lr
	.endasmfunc

ioBase: .int 0x40004C00
NVICBase: 	.int 0xE000E000
