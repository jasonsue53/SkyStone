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

            // HACK: hard-coded to RIGHT.
            //stoneconfig = "RIGHT";

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

            // Scan in place for a second.
                scanStones();
                printStatus();
                encoderDrive(PEEKABOO_DISTANCE, PEEKABOO_DISTANCE, 0.8f, 0.0f);
                numMoved++;
                sleep(500);
                scanStones();
                printStatus();

            if (stoneconfig == "LEFT") {

                //drive forward to pick up skystone, making sure to subtract the amount we already inched forward for peek_aboo.
                float firstDistance = 29 - (numMoved * PEEKABOO_DISTANCE);
                encoderDrive(firstDistance, firstDistance, 0.8, 0.0f);

                // pick up first skystone
                dropFrontFinger();
                sleep(1500);

                // back up a bit, and turn right towards the bridge
                encoderDrive(-7);
                turnRightAbsolute(90.0f);

                // drive under the bridge, raise the finger so the stone can fall out, but don't wait until the finger is all
                // the way up before we move back - we can roll back at the same time.
                encoderDrive(53, 53, 1.0, 90.0f);
                raiseFrontFinger();
                //turnLeftAbsolute(90.0f);
                encoderDrive(-76, -76, 1.0, 90.0f);

                // turn left back towards the stones.
                turnLeftAbsolute(0.0f);
                turnLeft(10);
                encoderDrive(6);

                // pick up second skystone.
                dropFrontFinger();
                sleep(1500);

                // back up a bit, and turn right towards the bridge
                encoderDrive(-7);
                turnRightAbsolute(90.0f);

                // drive under the bridge again, and once again raise the finger and start racing back towards the line.
                encoderDrive(80, 80, 1.0, 90.0f);
                raiseFrontFinger();
                encoderDrive(-26, -26, 1.0,135.0f);

                turnToAngle(90.0f);

            } else if (stoneconfig =="CENTER") {  //VERIFIED WORKS

                float firstDistance = 6 - (numMoved * PEEKABOO_DISTANCE);
                encoderDrive(firstDistance, firstDistance, 0.8, 0.0f);
                turnRightAbsolute(45);
                encoderDrive(12, 12, 0.8, 45.0f);
                turnLeftAbsolute(0);
                //encoderDrive(12);
                encoderDrive(15, 15, 0.8, 0.0f);

                // pick up first skystone
                dropFrontFinger();
                sleep(1500);

                // back up a bit, and turn right towards the bridge
                encoderDrive(-6);
                turnRightAbsolute(90.0f);


                // drive under the bridge, raise the finger so the stone can fall out, but don't wait until the finger is all
                // the way up before we move back - we can roll back at the same time.
                encoderDrive(45, 45, 1.0, 90.0f);
                raiseFrontFinger();
                encoderDrive(-69, -69, 1.0, 90.0f);


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
                encoderDrive(69, 69, 1.0, 90.0f);
                raiseFrontFinger();

                encoderDrive(-19, -19, 1.0,135.0f);

                turnToAngle(90.0f);

            } else {

                float firstDistance = 6 - (numMoved * PEEKABOO_DISTANCE);
                encoderDrive(firstDistance, firstDistance, 0.8, 0.0f);
                turnRightAbsolute(45);
                encoderDrive(22, 22, 0.8, 45.0f);
                turnLeftAbsolute(0);
                //encoderDrive(12);
                encoderDrive(8.5f, 8.5f, 0.8, 0.0f);

                // pick up first skystone
                dropFrontFinger();
                sleep(1500);


                //back up a bit, and turn right towards the bridge
                encoderDrive(-6);
                turnRightAbsolute(90.0f);


                // drive under the bridge, raise the finger so the stone can fall out, but don't wait until the finger is all
                // the way up before we move back - we can roll back at the same time.
                encoderDrive(40, 40, 1.0, 90.0f);
                turnLeftAbsolute(90.0f);

                raiseFrontFinger();
                encoderDrive(-64, -64, 1.0, 90.0f);



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
                encoderDrive(69, 69, 1.0, 90.0f);

                raiseFrontFinger();

                encoderDrive(-26, -26, 1.0,135.0f);

                turnToAngle(90.0f);

            }

            madeTheRun = true;
        }

        printStatus();
    }
}

