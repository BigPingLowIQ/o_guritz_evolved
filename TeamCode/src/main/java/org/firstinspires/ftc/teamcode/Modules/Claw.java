package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw {

    public static double leftPosOpen , leftPosClosed , rightPosOpen , rightPosClosed;
    enum State{
        NONE , LEFT , RIGHT , BOTH;
    }
    State state=State.BOTH;
    Servo leftClaw , rightClaw;
    Gamepad gamepad;
    public Claw(HardwareMap hm , Gamepad gamepad)
    {
        this.gamepad=gamepad;
        leftClaw=hm.get(Servo.class , "");
        rightClaw=hm.get(Servo.class , "");
    }
    private void updateState()
    {
        if(gamepad.a)//doar pentru claw stanga
        {
            switch(state)
            {
                case NONE: state=State.LEFT;
                break;
                case LEFT: state=State.NONE;
                break;
                case BOTH: state=State.RIGHT;
                break;
                case RIGHT:state=State.BOTH;
                break;
            }
        }
        if(gamepad.b)// doar pentru claw dreapta
        {
            switch(state)
            {
                case NONE: state=State.RIGHT;
                    break;
                case RIGHT: state=State.NONE;
                    break;
                case BOTH: state=State.LEFT;
                    break;
                case LEFT:state=State.BOTH;
                    break;
            }
        }
        if(gamepad.dpad_down)//pentru ambele
        {
            switch(state)
            {
                case NONE:
                case RIGHT:
                case LEFT:state=State.BOTH;
                break;
                case BOTH:
                    state=State.NONE;
            }
        }
    }
    private void updateHardware()
    {
        switch(state)
        {
            case NONE:
                leftClaw.setPosition(leftPosOpen);
                rightClaw.setPosition(rightPosOpen);
                break;
            case BOTH:
                leftClaw.setPosition(leftPosClosed);
                rightClaw.setPosition(rightPosClosed);
                break;
            case LEFT:
                leftClaw.setPosition(leftPosClosed);
                rightClaw.setPosition(rightPosOpen);
                break;
            case RIGHT:
                leftClaw.setPosition(leftPosOpen);
                rightClaw.setPosition(rightPosClosed);
                break;
        }
    }
    public void update()
    {
        updateState();
        updateHardware();
    }
}
