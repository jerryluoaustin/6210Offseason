package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="TeleOpMode", group = "TeleOp")
public class TeleOpMode extends OpMode
{

    DcMotor FL;
    DcMotor FR;
    DcMotor BR;
    DcMotor BL;

    public void init()
    {
        FR = hardwareMap.dcMotor.get("FR");
        FL = hardwareMap.dcMotor.get("FL");
        BR = hardwareMap.dcMotor.get("BR");
        BL = hardwareMap.dcMotor.get("BL");

        FR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        telemetry.addData("init ", "completed");
        telemetry.update();
    }

    public void RunAllMotors(double power)
    {
        FR.setPower(power);
        FL.setPower(power);
        BR.setPower(power);
        BL.setPower(power);
    }

    public void StopAllMotors()
    {
        FR.setPower(0);
        FL.setPower(0);
        BR.setPower(0);
        BL.setPower(0);
    }

    double flp = 0;
    double frp = 0;
    double blp = 0;
    double brp = 0;


    @Override
    public void loop()
    {
        if (Math.abs(gamepad1.left_stick_y) > 0.1)
        {
            flp = gamepad1.left_stick_y;
            frp = gamepad1.left_stick_y;
            blp = gamepad1.left_stick_y;
            brp = gamepad1.left_stick_y;
        }

        if(gamepad1.right_stick_x > 0.1)
        {
            flp += Math.abs(gamepad1.right_stick_x);
            blp += Math.abs(gamepad1.right_stick_x);
        }

        if(gamepad1.right_stick_x < -0.1)
        {
            frp += Math.abs(gamepad1.right_stick_x);
            brp += Math.abs(gamepad1.right_stick_x);
        }

        FR.setPower(frp);
        FL.setPower(flp);
        BR.setPower(brp);
        BL.setPower(blp);

    }


}
