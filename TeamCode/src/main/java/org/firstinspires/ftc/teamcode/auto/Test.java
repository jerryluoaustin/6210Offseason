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

        Pose2d startPose = new Pose2d(-24, -70, Math.toRadians(90));

        drive.setPoseEstimate(startPose);

        TrajectorySequence myTrajectory = drive.trajectorySequenceBuilder(startPose)

                .lineToLinearHeading(new Pose2d(-59.75, -62.5, Math.toRadians(229)))
                .waitSeconds(2)
                .lineToLinearHeading(new Pose2d(-11.3, -36, Math.toRadians(98.5)))
                .waitSeconds(1)
                .lineToLinearHeading(new Pose2d(10.3, -59.2, Math.toRadians(10)))
                .waitSeconds(1)
                .lineToLinearHeading(new Pose2d(50, -56, Math.toRadians(12)))
                .build();

        waitForStart();

        if(isStopRequested()) return;

        drive.followTrajectorySequence(myTrajectory);
    }
}
