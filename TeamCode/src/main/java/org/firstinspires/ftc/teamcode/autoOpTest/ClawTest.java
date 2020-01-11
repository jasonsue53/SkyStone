package org.firstinspires.ftc.teamcode.autoOpTest;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.auto.ChassisConfig;
import org.firstinspires.ftc.teamcode.auto.ChassisStandard;

/**
 *
 */
@Autonomous(name="Claw Test", group="ZZTesting")
public class ClawTest extends ChassisStandard {
    public static int sleepTime = 1500;

    @Override
    public void makeTheRun () {

        for (int i = 0 ; i < 3 ; i++) {
            raiseCrab();
            sleep(sleepTime);

            dropCrab();
            sleep(sleepTime);
        }

        raiseCrab();
    }
}

