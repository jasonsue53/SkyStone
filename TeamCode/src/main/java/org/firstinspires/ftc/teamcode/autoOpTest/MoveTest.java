package org.firstinspires.ftc.teamcode.autoOpTest;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.auto.ChassisConfig;
import org.firstinspires.ftc.teamcode.auto.ChassisStandard;

/**
 *
 */
@Autonomous(name="Move Test", group="ZZTesting")
public class MoveTest extends ChassisStandard {

    public static int sleepTime = 5000;

    @Override
    public void makeTheRun () {
        // forward one square.
        encoderDrive(24);
        sleep(sleepTime);

        // back to the starting location.
        encoderDrive(-24);
        sleep(sleepTime);
    }
}

