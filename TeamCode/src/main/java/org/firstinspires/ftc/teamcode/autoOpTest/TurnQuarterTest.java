package org.firstinspires.ftc.teamcode.autoOpTest;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.auto.ChassisStandard;
import org.firstinspires.ftc.teamcode.auto.ChassisConfig;

import java.util.List;

/**
 *
 */
@Autonomous(name="Turn Delta Quarter Test", group="ZZTesting")
public class TurnQuarterTest extends ChassisStandard {

    public static int sleepTime = 2000;

    @Override
    public void makeTheRun () {
        turnRight(90);
        sleep(sleepTime);
        turnLeft(90);
        sleep(sleepTime);
    }
}

