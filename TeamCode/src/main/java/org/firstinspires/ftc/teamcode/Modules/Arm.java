package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arm {

    public static double downPos , climbPos, backdropPos;
    enum State{
        Down(downPos) , Climb(climbPos) , Backdrop(backdropPos);
        double position;
        State(double position)
        {
            this.position=position;
        }
    }State state;
    DcMotorEx motor1 , motor2;
    PID pid;
    DcMotorEx encoder;
    Gamepad gamepad;
    DcMotorEx climb;
    public Arm(HardwareMap hm , Gamepad gamepad)
    {
        this.gamepad=gamepad;
        state=State.Down;
        motor1=hm.get(DcMotorEx.class , "");
        motor2=hm.get(DcMotorEx.class , "");
        motor1.setDirection(DcMotorSimple.Direction.REVERSE);
        encoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        encoder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        climb=hm.get(DcMotorEx.class , "");
    }
    private void updateStateValues(){

    }
    private void updateState()
    {
        if(gamepad.dpad_down)state=State.Climb;

        if(gamepad.a)
        {
            switch(state)
            {
                case Down:
                state=State.Backdrop;
                break;
                case Backdrop:
                state=State.Down;
                break;
                case Climb:
                    break;
            }
        }
    }
    private void updateHardware()
    {
        double power=pid.pidControl(encoder.getCurrentPosition() , state.position);
        motor2.setPower(power);
        motor1.setPower(power);
        if(state==State.Climb && gamepad.dpad_down)climb.setPower(-0.3);
    }

    public void update()
    {
        updateStateValues();
        updateHardware();
        updateState();
    }
}
