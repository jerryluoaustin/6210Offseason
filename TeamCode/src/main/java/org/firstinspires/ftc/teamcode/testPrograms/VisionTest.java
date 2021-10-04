package org.firstinspires.ftc.teamcode.testPrograms;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.FreightFrenzyLinearOpMode;

@Autonomous(name="TestControlHub", group = "auto") // BLUE SIDE
@Disabled

public class VisionTest extends FreightFrenzyLinearOpMode {

    int hold;

    @Override
    public void runOpMode() throws InterruptedException {

        init(hardwareMap, -1);
        initOpenCV();

        waitForStart();

        //while (!isStopRequested() && opModeIsActive()){
            hold = detectStack();
        //}

        sleep(1000);

        driveDistance(0.5,5,5);
        telemetry.addData("program done",": complete");
        telemetry.update();

    }
}
