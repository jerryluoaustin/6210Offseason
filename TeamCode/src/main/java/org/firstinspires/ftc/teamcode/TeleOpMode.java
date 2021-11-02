package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.HashMap;

@TeleOp(name="TeleOpMode", group = "TeleOp")
public class TeleOpMode extends OpMode
{

    DcMotor FL;
    DcMotor FR;
    DcMotor BR;
    DcMotor BL;

    //Driver Two Controller (mechanisms)
    DcMotor LL;
    DcMotor RL;


    //Intake motor
    DcMotor Intake;


    // right carousel and left carousel servo declaration
    CRServo RC;
    CRServo LC;

    // left and right gate servos
    Servo RG;
    Servo LG;

    // Lowest encoder position
    int lowest = 0;
    // Highest encoder position
    int highest = 0;


    public void init()
    {
        FR = hardwareMap.dcMotor.get("rightFront");
        FL = hardwareMap.dcMotor.get("leftFront");
        BR = hardwareMap.dcMotor.get("rightRear");
        BL = hardwareMap.dcMotor.get("leftRear");

        LL = hardwareMap.dcMotor.get("leftLift");
        RL = hardwareMap.dcMotor.get("rightLift");


        Intake = hardwareMap.dcMotor.get("Intake");


        RC = hardwareMap.crservo.get("rightCarousel");
        LC = hardwareMap.crservo.get("leftCarousel");

        // servo for left and right gate
        RG = hardwareMap.servo.get("rightGate");
        LG = hardwareMap.servo.get("leftGate");



        FR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        FR.setDirection(DcMotorSimple.Direction.REVERSE);
        FL.setDirection(DcMotorSimple.Direction.FORWARD);
        BR.setDirection(DcMotorSimple.Direction.REVERSE);
        BL.setDirection(DcMotorSimple.Direction.FORWARD);

        //LL.setDirection(DcMotorSimple.Direction.REVERSE);

        LL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

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

    //Lift method.
    public void liftTest()
    {
        LL.setPower(-gamepad2.right_stick_y);
        RL.setPower(-gamepad2.right_stick_y);
    }

    public void liftStop()
    {
        LL.setPower(0);
        RL.setPower(0);
    }

    public void automaticLift(int pos, boolean front)
    {
        int tarPos = 0;

        if (front) tarPos = lowest + pos;
        else tarPos = highest - pos;

        // Takes in encoder position to move lift to
        LL.setTargetPosition(tarPos);
        RL.setTargetPosition(tarPos);
        LL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LL.setPower(0.5);
        RL.setPower(0.5);
    }

    //Stop carousel
    public void carousel()
    {
        LC.setPower(0);
        RC.setPower(0);
    }
    public void closeGate()
    {
        RG.setPosition(1);
        LG.setPosition(0);
    }
    public void openGate()
    {
        RG.setPosition(0);
        LG.setPosition(1);
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


    //Intake go
    public void goIntake(double speed)
    {
        Intake.setPower(speed);
    }

    //Stop intake
    public void stopIntake()
    {
        Intake.setPower(0);
    }



    //variable checking if the gate is closed.
    boolean gateClosed = true;
    //variable controlling the direction to spin the carousel
    double duckDirection = 1;
    //variable to control whether it will intake or outtake the freight
    double intakeDirection = 1;

    public HashMap<String, Boolean> buttons = new HashMap<String, Boolean>();


    @Override
    public void loop()
    {
        // Get the bounds of the encoder value of the lift
        if (LL.getCurrentPosition() < lowest) {
            lowest = LL.getCurrentPosition();
        }

        if (LL.getCurrentPosition() > highest) {
            highest = LL.getCurrentPosition();
        }

        // Auto arm variables
        // Encoder positions for each level
        final int highFront = 0;
        final int midFront = 0;
        final int lowFront = 0;
        final int highRear = 0;
        final int midRear = 0;
        final int lowRear = 0;

        int high = highFront;
        int mid = midFront;
        int low = lowFront;
        boolean front = true;

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


        //Lift go
        /*
        if (Math.abs(gamepad2.right_stick_y) > 0.1) {
            liftTest();
            telemetry.addData("Hi", RL.getPower());
            telemetry.addData("Hello", LL.getPower());
        }
        else
        {
            liftStop();
        }*/

        // Change lift positions
        if (isPressed("x", gamepad2.x)) {
            if (front) {
                high = highFront;
                mid = midFront;
                low = lowFront;
                front = false;
            }else {
                high = highRear;
                mid = midRear;
                low = lowRear;
                front = true;
            }
        }
        //Lift High

        if (isPressed("y", gamepad2.y))
        {
            automaticLift(high, front);
        }


        //Lift Mid
        if (isPressed("b", gamepad2.b))
        {
            automaticLift(mid, front);
        }

        //Lift Low
        if (isPressed("a", gamepad2.a))
        {
            automaticLift(low, front);
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
            RC.setPower(-0.85);
            LC.setPower(0.85);
        }
        //Red Carousel
        else if (gamepad1.left_bumper)
        {
            RC.setPower(0.85);
            LC.setPower(-0.85);
        }
        else
        {
            RC.setPower(0);
            LC.setPower(0);
        }



        //Gate
        if (isPressed("1a", gamepad1.a) && gateClosed == true)
        {
            openGate();
            gateClosed = false;
        }
        else if (isPressed("1a", gamepad1.a) && gateClosed == false)
        {
            closeGate();
            gateClosed = true;
        }


        //Changes to outtake
        if (gamepad1.x)
        {
            intakeDirection += -1;
        }

        //Intake
        if ((gamepad1.left_trigger) > 0.1)
        {
            goIntake(gamepad1.left_trigger * intakeDirection);
        }
        //Stop Intake
        else
        {
            stopIntake();
        }


        telemetry.addData("Right Bumper (Right Carousel):", RC.getPower());
        telemetry.addData("Right Bumper (Left Carousel):", LC.getPower());
        telemetry.addData("L encoder", LL.getCurrentPosition());
        telemetry.addData("R encoder", RL.getCurrentPosition());

        telemetry.update();

    }


}
