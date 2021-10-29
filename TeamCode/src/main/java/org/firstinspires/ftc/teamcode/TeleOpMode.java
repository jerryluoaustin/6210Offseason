package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

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

        FL.setDirection(DcMotorSimple.Direction.FORWARD);
        BR.setDirection(DcMotorSimple.Direction.REVERSE);
        BL.setDirection(DcMotorSimple.Direction.FORWARD);

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

    //Lift method
    public void liftTest()
    {
        LL.setPower(0.05);
        RL.setPower(0.05);
    }

    public void liftStop()
    {
        LL.setPower(0);
        RL.setPower(0);
    }

    //Stop carousel
    public void carousel()
    {
        LC.setPower(0);
        RC.setPower(0);
    }
    public void closeGate()
    {
        RG.setPosition(0);
        LG.setPosition(0);
    }
    public void openGate()
    {
        RG.setPosition(1);
        LG.setPosition(1);
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


    @Override
    public void loop()
    {
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
        if (gamepad2.x)
        {
            liftTest();
        }

        //Lift stop
        if (gamepad2.b)
        {
            liftStop();
        }


        //Changes direction of carousel
        if (gamepad1.b)
        {
            duckDirection *= -1;
        }

        //Carousel
        if ((gamepad1.right_trigger) > 0.1)
        {
            RC.setPower(1 * duckDirection);
            LC.setPower(1 * duckDirection);
        }
        else
        {
            carousel();
        }


        //Gate
        if (gamepad2.a && gateClosed == true)
        {
            openGate();
            gateClosed = false;

        }
        else if (gamepad2.a && gateClosed == false)
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


        telemetry.addData("Right Trigger:", gamepad1.right_trigger);
        telemetry.update();



    }


}
