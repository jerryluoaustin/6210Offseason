package org.firstinspires.ftc.teamcode.testPrograms;

import static android.graphics.Color.blue;
import static android.graphics.Color.green;
import static android.graphics.Color.red;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.AutoBase;
import org.firstinspires.ftc.teamcode.FreightFrenzyLinearOpMode;
import org.firstinspires.ftc.teamcode.OpenCV;
import org.firstinspires.ftc.teamcode.VuforiaBM;

@Autonomous(name="TestControlHub", group = "auto") // BLUE SIDE
//@Disabled

public class VisionTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        //OpenCV openCV = new OpenCV(this);
        VuforiaBM vuforia = new VuforiaBM(this);


        waitForStart();

        Bitmap bm = vuforia.getBitmap();

        int pixel2 = bm.getPixel(640, 360);
        int pixel1 = bm.getPixel(0, 450);
        telemetry.addData("center pixel", pixel1);
        telemetry.addData("left pixel", pixel2);

        telemetry.addData("Lred", red(pixel1));
        telemetry.addData("Lgreen", green(pixel1));
        telemetry.addData("Lblue", blue(pixel1));

        telemetry.addData("Rred", red(pixel2));
        telemetry.addData("Rgreen", green(pixel2));
        telemetry.addData("Rblue", blue(pixel2));


        telemetry.addData("program done",": complete");
        telemetry.update();
        sleep(10000);

    }
}
