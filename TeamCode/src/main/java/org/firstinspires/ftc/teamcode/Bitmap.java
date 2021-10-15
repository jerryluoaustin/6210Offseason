package org.firstinspires.ftc.teamcode;

import com.vuforia.PIXEL_FORMAT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;


import static android.graphics.Color.blue;
import static android.graphics.Color.green;
import static android.graphics.Color.red;

public class Bitmap {

    private AutoBase auto;

    public Bitmap(AutoBase auto){
        this.auto = auto;
        //Create vuforia object
        VuforiaLocalizer vuforia = null;

        //Add initialization
        int cameraMonitorViewId = auto.hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", auto.hardwareMap.appContext.getPackageName());

        //LogitechC310 = hardwareMap.get(WebcamName.class, "Logitech C310");

        //localizer for webcam
        VuforiaLocalizer.Parameters paramWC = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        paramWC.vuforiaLicenseKey = "AQt2xVL/////AAABmXIVKUnTcEJbqvVBjp/Sw/9SqarohYyKotzRjT/Xl1/S8KDwsFHv/zYw6rXqXTjKrnjk92GfBA4hbZaQP17d1N6BiBuXO2W/hFNoMGxiF+fWlnvtDmUM1H/MF9faMOjZcPNjnQ7X8DVwdDDha3A3aqaoegefkKxb4A5EjP8Xcb0EPJ1JA4RwhUOutLbCDJNKUq6nCi+cvPqShvlYTvXoROcOGWSIrPxMEiOHemCyuny7tJHUyEg2FTd2upiQygKAeD+LN3P3cT02aK6AJbQ0DlQccxAtoo1+b//H6/eGro2s0fjxA2dH3AaoHB7qkb2K0Vl7ReFEwX7wmqJleamNUG+OZu7K3Zm68mPudzNuhAWQ";
        //paramWC.cameraName = LogitechC310;
        vuforia = ClassFactory.getInstance().createVuforia(paramWC);

        Vuforia.setFrameFormat(PIXEL_FORMAT.RGB565, true); //enables RGB565 format for the image
        vuforia.setFrameQueueCapacity(1); //tells VuforiaLocalizer to only store one frame at a time
    }

    public void Test() {
        // do stuff...
    }
}
