package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Manipulators {

    private HardwareMap robot = null;

    //Lift
    private DcMotor RL = null;
    // Lowest encoder position
    private int lowest = 0;
    // Highest encoder position
    private int highest = 0;

    private final int highFront = 999;
    private final int midFront = 607;
    private final int lowFront = 293;
    private final int highRear = 3220;
    private final int midRear = 3648;
    private final int lowRear = 4000;

    private int high = highFront;
    private int mid = midFront;
    private int low = lowFront;
    private boolean front = true;


    //Intake motor
    private DcMotor IT;

    //Intake servos
    private Servo FI;
    private Servo BI;

    // right carousel and left carousel servo declaration
    private CRServo RC;
    private CRServo LC = null;

    // left and right gate servos
    private Servo RG = null;
    private Servo LG = null;

    public Manipulators(HardwareMap robot) {
        this.robot = robot;

        RL = robot.get(DcMotor.class, "rightLift");

        IT = robot.get(DcMotor.class, "intake");

        RC = robot.get(CRServo.class, "rightCarousel");
        LC = robot.get(CRServo.class, "leftCarousel");

        // servo for left and right gate
        RG = robot.get(Servo.class, "rightGate");
        LG = robot.get(Servo.class, "leftGate");

        //intake servos
        FI = robot.get(Servo.class, "frontIntake");
        BI = robot.get(Servo.class, "backIntake");


        // Lift
        RL = robot.get(DcMotor.class, "rightLift");
        RL.setDirection(DcMotorSimple.Direction.REVERSE);
        RL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void checkPosition() {
        // Get the bounds of the encoder value of the lift
        if (RL.getCurrentPosition() < lowest) {
            lowest = RL.getCurrentPosition();
        }

        if (RL.getCurrentPosition() > highest) {
            highest = RL.getCurrentPosition();
        }
    }

    public void changeLiftVars() {
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

    // int pos : 0 - return/retract || 1 - low || 2 - mid || 3 - high
    public void automaticLift(int pos)
    {
        int tarPos = 0;
        int height = 0;


        if (pos == 0) height = 0;

        if (front){
            if (pos == 1) height = lowFront;
            if (pos == 2) height = midFront;
            if (pos == 3) height = highFront;
        }
        else {
            if (pos == 1) height = lowRear;
            if (pos == 2) height = midRear;
            if (pos == 3) height = highRear;
        }

        tarPos = lowest + height;

        // Takes in encoder position to move lift to
        RL.setTargetPosition(tarPos);
        RL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RL.setPower(1);
    }

    public boolean liftIsBusy() {
        return RL.isBusy();
    }

    public void manualLift(double y) {
        RL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RL.setPower(y);
    }
    public void stopLift() {
        RL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RL.setPower(0);
    }

    public void redCarousel(){
        RC.setPower(0.85);
        LC.setPower(0.85);
    }

    public void blueCarousel(){
        RC.setPower(-0.85);
        LC.setPower(-0.85);
    }

    public void carouselStop(){
        RC.setPower(0);
        LC.setPower(0);
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


    public void intakeControl(double frontServo, double backServo)
    {
        FI.setPosition(frontServo);
        BI.setPosition(backServo);
    }

    public void intake(boolean out) {
        if (out) IT.setPower(1);
        else IT.setPower(-1);

    }

}
