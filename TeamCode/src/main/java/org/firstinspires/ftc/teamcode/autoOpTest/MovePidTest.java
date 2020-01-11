package org.firstinspires.ftc.teamcode.autoOpTest;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.auto.ChassisStandard;

/**
 *
 */
@Autonomous(name="Move Straight Test", group="ZZTesting")
public class MovePidTest extends ChassisStandard {

    public static int sleepTime = 1000;

    @Override
    public void makeTheRun () {

        //turnRight(15.0f);
        turnLeft(15.0f);

        // forward the entire field
        encoderDrive(96, 96, 1.0, 0.0f);
        sleep(sleepTime);

        turnLeft(15.0f);

        // reverse
        encoderDrive(-96, -96, 1.0, 0.0f);
        sleep(sleepTime);
    }
}

