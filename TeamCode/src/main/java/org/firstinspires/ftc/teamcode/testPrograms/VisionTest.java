package org.firstinspires.ftc.teamcode.testPrograms;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.AutoBase;
import org.firstinspires.ftc.teamcode.FreightFrenzyLinearOpMode;
import org.firstinspires.ftc.teamcode.OpenCV;

@Autonomous(name="TestControlHub", group = "auto") // BLUE SIDE
//@Disabled

public class VisionTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        OpenCV openCV = new OpenCV(this);

        waitForStart();

        telemetry.addData("program done",": complete");
        telemetry.update();

    }
}
