package org.firstinspires.ftc.teamcode.autoOpTest;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.auto.ChassisStandard;

/**
 *
 */
@Autonomous(name="Finger Test", group="ZZTesting")
public class FingerTest extends ChassisStandard {
    public static int sleepTime = 1000;

    @Override
    public void makeTheRun () {

        for (int i = 0 ; i < 3 ; i++) {
            raiseRightFinger();
            sleep(sleepTime);

            raiseLeftFinger();
            sleep(sleepTime);

            dropRightFinger();
            sleep(sleepTime);

            dropLeftFinger();
            sleep(sleepTime);
        }

        raiseRightFinger();
        raiseLeftFinger();
    }
}

