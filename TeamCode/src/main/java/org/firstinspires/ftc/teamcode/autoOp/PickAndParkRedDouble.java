package org.firstinspires.ftc.teamcode.autoOp;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.auto.ChassisStandard;

/**
 *
 */
@Autonomous(name="Pick and Park Red Double", group="OpMode")
public class PickAndParkRedDouble extends ChassisStandard {

    private static final int PEEKABOO_DISTANCE = 4;

    int numMoved;

    public PickAndParkRedDouble() {
        // override the default of vuforia being off.
        useVuforia = true;
        madeTheRun = false;
        numMoved = 0;
        // need this for tyler 2 chassis
       // switchMotorDirection();
    }

    /**
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        if (madeTheRun == false) {

            // HACK: hard-coded to CENTER.
            stoneconfig = "CENTER";

           /* int count = 0;
            while(stoneconfig.equals(UNKNOWN) && (count < 2)){
                scanStones();
                sleep(250);
                count++;
            }

            if ((numMoved < 2) && stoneconfig.equals(UNKNOWN)) {
                encoderDrive(PEEKABOO_DISTANCE);
                numMoved++;
                sleep(500); // TODO: consider making this smaller
                return;
            } */

            if (stoneconfig == "LEFT") {

                //drive forward to pick up skystone, making sure to subtract the amount we already inched forward for peek_aboo.
                encoderDrive(30 - (numMoved * PEEKABOO_DISTANCE));

                // pick up first skystone
                dropFrontFinger();
                sleep(1500);

                // back up a bit, and turn right towards the bridge
                encoderDrive(-7);
                turnRightAbsolute(90.0f);

                // drive under the bridge, raise the finger so the stone can fall out, but don't wait until the finger is all
                // the way up before we move back - we can roll back at the same time.
                encoderDrive(53, 53, 1.0);
                raiseFrontFinger();
                //turnLeftAbsolute(90.0f);
                encoderDrive(-76, -76, 1.0);

                // turn left back towards the stones.
                turnLeftAbsolute(0.0f);
                encoderDrive(6);

                // pick up second skystone.
                dropFrontFinger();
                sleep(1500);

                // back up a bit, and turn right towards the bridge
                encoderDrive(-7);
                turnRightAbsolute(90.0f);

                // drive under the bridge again, and once again raise the finger and start racing back towards the line.
                encoderDrive(80, 80, 1.0);
                raiseFrontFinger();
                encoderDrive(-20, -20, 1.0);
            }
            //VERIFIED WORKS
            else if (stoneconfig =="CENTER") {

                encoderDrive(6 - (numMoved *PEEKABOO_DISTANCE));
                turnRightAbsolute(45);
                encoderDrive(12);
                turnLeftAbsolute(0);
                //encoderDrive(12);
                encoderDrive(15);

                // pick up first skystone
                dropFrontFinger();
                sleep(1500);

                // back up a bit, and turn right towards the bridge
                encoderDrive(-6);
                turnRightAbsolute(90.0f);


                // drive under the bridge, raise the finger so the stone can fall out, but don't wait until the finger is all
                // the way up before we move back - we can roll back at the same time.
                encoderDrive(45, 45, 1.0);
                raiseFrontFinger();
                encoderDrive(-69, -69, 1.0);


                // turn left back towards the stones.
                turnLeftAbsolute(0.0f);
                encoderDrive(6);

                // pick up second skystone.
                dropFrontFinger();
                sleep(1500);


                // back up a bit, and turn right towards the bridge
                encoderDrive(-7);
                turnRightAbsolute(90.0f);


                // drive under the bridge again, and once again raise the finger and start racing back towards the line.
                encoderDrive(69, 69, 1.0);
                raiseFrontFinger();

                encoderDrive(-13, -13, 1.0);

            }
            //NOT TESTED - DOESN'T WORK
            else {

                /*encoderDrive(6);
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
                encoderDrive(-24);*/
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

            madeTheRun = true;
        }

        printStatus();
    }
}

