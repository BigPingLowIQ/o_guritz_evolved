package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveTrain {

    DcMotorEx leftTop , rightTop , leftDown , rightDown;
    Gamepad gamepad;
    public DriveTrain(HardwareMap hm , Gamepad gamepad)
    {
        this.gamepad=gamepad;
        leftDown=hm.get(DcMotorEx.class , "");
        leftTop=hm.get(DcMotorEx.class , "");
        rightDown=hm.get(DcMotorEx.class , "");
        rightTop=hm.get(DcMotorEx.class , "");

    }
    public void update()
    {
        double x=gamepad.left_stick_x;
        double y=-gamepad.left_stick_y;
        double rotate=gamepad.right_stick_x;

        leftDown.setPower(y-x+rotate);
        rightDown.setPower(y+x-rotate);
        leftTop.setPower(y+x+rotate);
        rightTop.setPower(y-x-rotate);

    }
}
