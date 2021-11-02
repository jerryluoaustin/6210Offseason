package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Manipulators {

    private LinearOpMode robot;
    //Driver Two Controller (mechanisms)
    private DcMotor LL;
    private DcMotor RL;


    //Intake motor
    private DcMotor Intake;


    // right carousel and left carousel servo declaration
    private CRServo RC;
    private CRServo LC;

    // left and right gate servos
    private Servo RG;
    private Servo LG;

    public Manipulators(LinearOpMode robot) {
        this.robot = robot;

        LL = robot.hardwareMap.dcMotor.get("leftLift");
        RL = robot.hardwareMap.dcMotor.get("rightLift");

        Intake = robot.hardwareMap.dcMotor.get("Intake");


        RC = robot.hardwareMap.crservo.get("rightCarousel");
        LC = robot.hardwareMap.crservo.get("leftCarousel");

        // servo for left and right gate
        RG = robot.hardwareMap.servo.get("rightGate");
        LG = robot.hardwareMap.servo.get("leftGate");

        LL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void blueCarousel(){
        RC.setPower(-0.85);
        LC.setPower(0.85);
    }

    public void redCarousel(){
        RC.setPower(0.85);
        LC.setPower(-0.85);
    }

    public void carouselStop(){
        RC.setPower(0);
        LC.setPower(0);
    }
}
