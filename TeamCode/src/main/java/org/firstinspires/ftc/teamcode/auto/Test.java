package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.AutoBase;

@Autonomous(name = "Test", group = "testTest")
public class Test extends AutoBase {

    public static double DISTANCE = 50;

    @Override
    public void runOpMode() throws InterruptedException {

        // Use the instances created in AutoBase
        openCV.Test();

        waitForStart();

        bitmap.capPositionReturn();

        Trajectory trajectoryForward = autoDrive.trajectoryBuilder(new Pose2d())
                .forward(DISTANCE)
                .build();

        Trajectory trajectoryBackward = autoDrive.trajectoryBuilder(trajectoryForward.end())
                .back(DISTANCE)
                .build();

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            autoDrive.followTrajectory(trajectoryForward);
            autoDrive.followTrajectory(trajectoryBackward);
        }

        telemetry.addData("Test done",": complete");
        telemetry.update();
    }
}
