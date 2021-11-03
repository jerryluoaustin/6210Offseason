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

        telemetry.addData("pos", vuforia.capPositionReturn());

        /*
        int pixel3 = blue(bm.getPixel(232, 600));
        int pixel2 = blue(bm.getPixel(698, 600));
        int pixel1 = blue(bm.getPixel(0, 0));

        telemetry.addData("3", pixel3);
        telemetry.addData("2", pixel2);
        telemetry.addData("1", pixel1);
        */

        telemetry.addData("program done",": complete");
        telemetry.update();
        sleep(10000);

    }
}
