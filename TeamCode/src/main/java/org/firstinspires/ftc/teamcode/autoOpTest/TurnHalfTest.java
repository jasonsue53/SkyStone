package org.firstinspires.ftc.teamcode.autoOpTest;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.auto.ChassisStandard;
import org.firstinspires.ftc.teamcode.auto.ChassisConfig;

/**
 *
 */
@Autonomous(name="Turn Delta Half Test", group="ZZTesting")
public class TurnHalfTest extends ChassisStandard {

    public static int sleepTime = 5000;

    @Override
    public void makeTheRun () {
        turnRight(180);
        sleep(sleepTime);

        turnLeft(180);
        sleep(sleepTime);
    }
}

