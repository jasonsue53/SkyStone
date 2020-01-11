package org.firstinspires.ftc.teamcode.autoOpTest;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.auto.ChassisStandard;

/**
 *
 */
@Autonomous(name="Turn Absolute Test", group="ZZTesting")
public class AbsoluteTurnTest extends ChassisStandard {

    public static int sleepTime = 1000;

    @Override
    public void makeTheRun () {
        turnRightAbsolute(90);
        sleep(sleepTime);
        turnRightAbsolute(180);
        sleep(sleepTime);
        turnRightAbsolute(270);
        sleep(sleepTime);
        turnRightAbsolute(0);
        sleep(sleepTime);
        turnRightAbsolute(90);
        sleep(sleepTime);

        turnLeftAbsolute(0);
        sleep(sleepTime);
        turnLeftAbsolute(270);
        sleep(sleepTime);
        turnLeftAbsolute(180);
        sleep(sleepTime);
        turnLeftAbsolute(90);
        sleep(sleepTime);
        turnLeftAbsolute(0);
        sleep(sleepTime);
        turnLeftAbsolute(270);
        sleep(sleepTime);
    }
}
