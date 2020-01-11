package org.firstinspires.ftc.teamcode.autoOpTest;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.auto.ChassisStandard;

@Autonomous(name="FingerStoneTest", group="ZZTesting")
public class TylerFingerStoneTest extends ChassisStandard {

    /**a
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop () {

        if (madeTheRun == false) {
            /*dropRightFinger();
            sleep(1000);
            dropLeftFinger();
            /*raiseRightFinger();
            sleep(1000);
            raiseLeftFinger();*/

            encoderDrive(-30);
            sleep(500);
            dropRightFinger();
            sleep(2000);
            encoderDrive(6);
            turnRight(75);
            encoderDrive(-60);
            raiseRightFinger();
            sleep(2000);
            encoderDrive(24);

            madeTheRun = true;
        }

        printStatus();
    }
}