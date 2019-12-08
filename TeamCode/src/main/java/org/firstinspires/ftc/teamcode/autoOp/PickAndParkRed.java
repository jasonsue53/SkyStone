package org.firstinspires.ftc.teamcode.autoOp;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.auto.ChassisConfig;
import org.firstinspires.ftc.teamcode.auto.ChassisStandard;

/**
 *
 */
@Autonomous(name="Pick and Park Red", group="OpMode")
public class PickAndParkRed extends ChassisStandard {

    public PickAndParkRed() {
        // override the default of vuforia being off.
        useVuforia = true;

        // need this for tyler 2 chassis
       // switchMotorDirection();
    }

    /**
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        if (madeTheRun == false) {

            scanStones();
            //VERIFIED WORKS
            if (stoneconfig == "LEFT") {

                encoderDrive(30);
                sleep(500);
                dropFrontFinger();
                sleep(2000);
                encoderDrive(-6);
                sleep(500);
                turnRight(95);
                sleep(500);

                encoderDrive(60);
                sleep(500);
                raiseFrontFinger();
                sleep(2000);
                encoderDrive(-24);


            }
            //VERIFIED WORKS
            else if (stoneconfig =="CENTER") {

               /* encoderDrive(6);
                turnRight(45);
                encoderDrive(8);
                turnLeft(42);
                encoderDrive(19);*/

                encoderDrive(30);
                sleep(500);

                sleep(500);
                dropBackFinger();
                sleep(2000);

                encoderDrive(-5);
                sleep(500);
                turnRight(95);
                sleep(500);
                encoderDrive(60);
                sleep(500);
                raiseBackFinger();
                sleep(2000);
                encoderDrive(-24);

            }
            //NOT TESTED - DOESN'T WORK
            else if (stoneconfig == "RIGHT") {

                encoderDrive(6);
                turnRight(45);
                encoderDrive(20);
                turnLeft(38);
                encoderDrive(13);

                sleep(500);
                dropBackFinger();
                sleep(2000);

                encoderDrive(-5);
                sleep(500);
                turnRight(95);
                sleep(500);
                encoderDrive(45);
                sleep(500);
                raiseBackFinger();
                sleep(2000);
                encoderDrive(-24);
            }

            madeTheRun = true;
        }

        printStatus();
    }
}

