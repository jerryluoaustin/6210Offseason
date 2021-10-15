package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Disabled

public abstract class AutoMaster extends LinearOpMode {

    public VuforiaBM bitmap = new VuforiaBM(this);
    public OpenCV openCV = new OpenCV(this);

}