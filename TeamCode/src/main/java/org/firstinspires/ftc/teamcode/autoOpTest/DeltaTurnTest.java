package org.firstinspires.ftc.teamcode.autoOpTest;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.auto.ChassisStandard;

/**
 *
 */
@Autonomous(name="Turn Delta Test", group="ZZTesting")
public class DeltaTurnTest extends ChassisStandard {

    public static int sleepTime = 1500;

    @Override
    public void makeTheRun () {
        turnRight(90);
        sleep(sleepTime);
        turnRight(90);
        sleep(sleepTime);
        turnRight(90);
        sleep(sleepTime);
        turnRight(90);
        sleep(sleepTime);
        turnRight(90);
        sleep(sleepTime);

        turnLeft(90);
        sleep(sleepTime);
        turnLeft(90);
        sleep(sleepTime);
        turnLeft(90);
        sleep(sleepTime);
        turnLeft(90);
        sleep(sleepTime);
        turnLeft(90);
        sleep(sleepTime);
        turnLeft(90);
        sleep(sleepTime);
    }
}
