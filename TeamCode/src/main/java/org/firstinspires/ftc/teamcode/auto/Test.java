package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.AutoBase;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous(name = "Test", group = "testTest")
public class Test extends LinearOpMode {

    public static double DISTANCE = 50;

    @Override
    public void runOpMode() {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        TrajectorySequence myTrajectory = drive.trajectorySequenceBuilder(new Pose2d())

                .lineToLinearHeading(new Pose2d(-50.6, -41.1, Math.toRadians(180)))
                //.waitSeconds(3)
                //.lineToLinearHeading(new Pose2d(-11.3, -42.9, Math.toRadians(90)))
                //.waitSeconds(3)
                //.lineToLinearHeading(new Pose2d(10.3, -63.9, Math.toRadians(0)))
                //.lineToLinearHeading(new Pose2d(37, -63.9, Math.toRadians(0)))
                .build();

        waitForStart();

        if(isStopRequested()) return;

        drive.followTrajectorySequence(myTrajectory);
    }
}
