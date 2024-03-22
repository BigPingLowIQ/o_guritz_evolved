package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

public class TeleOp extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Arm arm=new Arm (hardwareMap , gamepad1);
        DriveTrain driveTrain=new DriveTrain (hardwareMap , gamepad1 );
        Claw claw=new Claw(hardwareMap , gamepad2);
        waitForStart();
        if(isStopRequested())return;
        while(opModeIsActive())
        {
            arm.update();
            driveTrain.update();
            claw.update();
        }

    }
}
