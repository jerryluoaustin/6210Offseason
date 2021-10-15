package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.AutoBase;

@Autonomous(name = "Test2", group = "testTest")
public class Test2 extends AutoBase {
    @Override
    public void runOpMode() throws InterruptedException {

        // This is a variate on Test
        bitmap.Test();

        openCV.Test();

        waitForStart();

        telemetry.addData("Test 2 done",": complete");
        telemetry.update();
    }
}
