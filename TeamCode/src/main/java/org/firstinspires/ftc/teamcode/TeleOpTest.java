package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.HashMap;

@TeleOp(name="TeleOpTest", group = "TeleOp")
public class TeleOpTest extends OpMode
{
    Manipulators manip;
    DcMotor FL;
    DcMotor FR;
    DcMotor BR;
    DcMotor BL;

    //variable checking if the gate is closed.
    boolean gateClosed = true;
    //variable controlling the direction to spin the carousel
    double duckDirection = 1;
    //variable to control whether it will intake or outtake the freight
    double intakeDirection = 1;

    boolean manual = true;

    public HashMap<String, Boolean> buttons = new HashMap<String, Boolean>();

    public void init()
    {
        manip = new Manipulators(hardwareMap);

        FR = hardwareMap.dcMotor.get("rightFront");
        FL = hardwareMap.dcMotor.get("leftFront");
        BR = hardwareMap.dcMotor.get("rightRear");
        BL = hardwareMap.dcMotor.get("leftRear");

        telemetry.addData("init ", "completed");
        telemetry.update();
    }

    public boolean isPressed(String name, boolean button){
        boolean output = false;

        //If the hashmap doesn't already contain the key
        if (!buttons.containsKey(name)){
            buttons.put(name, false);
        }

        boolean buttonWas = buttons.get(name);
        if (button != buttonWas && button == true){
            output = true;
        }

        buttons.put(name, button);

        return output;
    }

    @Override
    public void loop()
    {

        // Auto arm variables
        // Encoder positions for each level

        double leftY = 0;
        double leftX = 0;
        double rightX = 0;
        double[] motorPower = new double[4];


        if (Math.abs(gamepad1.left_stick_y) > 0.1)
        {
            leftY = gamepad1.left_stick_y;
        }

        if (Math.abs(gamepad1.left_stick_x) > 0.1)
        {
            leftX = gamepad1.left_stick_x;
        }

        if (Math.abs(gamepad1.right_stick_x) > 0.1)
        {
            rightX = gamepad1.right_stick_x;
        }

        motorPower[0] = leftY + leftX + rightX;
        motorPower[1] = leftY - leftX - rightX;
        motorPower[2] = leftY - leftX + rightX;
        motorPower[3] = leftY + leftX - rightX;

        FR.setPower(motorPower[0]);
        FL.setPower(motorPower[1]);
        BR.setPower(motorPower[2]);
        BL.setPower(motorPower[3]);


        //manual lift

        if (Math.abs(gamepad2.right_stick_y) > 0.1) {
            manip.manualLift(gamepad2.right_stick_y);
        }
        else if (manual)
        {
            manip.stopLift();
        }

        // Change lift positions
        if (isPressed("x", gamepad2.x)) {
            manip.changeLiftVars();
        }

        // Auto lift
        //Lift High

        if (isPressed("y", gamepad2.y))
        {
            manual = false;
            manip.automaticLift(3);
        }

        //Lift Mid
        if (isPressed("b", gamepad2.b))
        {
            manual = false;
            manip.automaticLift(2);
        }

        //Lift Low
        if (isPressed("a", gamepad2.a))
        {
            manual = false;
            manip.automaticLift(1);
        }

/*
        //Changes direction of carousel
        if (gamepad1.b)
        {
            duckDirection *= -1;
        }
*/
        //Blue Carousel
        if (gamepad1.right_bumper)
        {
            manip.blueCarousel();
        }
        //Red Carousel
        else if (gamepad1.left_bumper)
        {
            manip.redCarousel();
        }
        else
        {
            manip.carouselStop();
        }



        //Gate
        if (isPressed("1a", gamepad1.a) && gateClosed == true)
        {
            manip.openGate();
            gateClosed = false;
        }
        else if (isPressed("1a", gamepad1.a) && gateClosed == false)
        {
            manip.closeGate();
            gateClosed = true;
        }


        //Changes to outtake
        if (gamepad1.x)
        {
            intakeDirection += -1;
        }
        /*
        //Intake
        if ((gamepad1.left_trigger) > 0.1)
        {
            goIntake();
        }
        //Stop Intake
        else
        {
            stopIntake();
        }
        */

        // Switch back to manual lift
        if (Math.abs(gamepad2.right_stick_y) > 0.1) manual = true;

        telemetry.update();

    }


}
