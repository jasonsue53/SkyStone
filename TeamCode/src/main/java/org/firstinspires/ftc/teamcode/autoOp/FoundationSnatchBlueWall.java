package org.firstinspires.ftc.teamcode.autoOp;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.auto.ChassisStandard;

/**
 *
 */
@Autonomous(name="FoundationSnatchBlueWall", group="OpMode")
public class FoundationSnatchBlueWall extends ChassisStandard {

    /**
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop () {

        if (madeTheRun == false) {

            /*encoderDrive(36); */
            switchMotorDirection();

            raiseCrab();
            sleep(1000);
            encoderDrive(50);
            turnLeft(90);
            encoderDrive(9);
            dropCrab();
            sleep(1000);
            turnLeft(65);
            encoderDrive(24);

            raiseCrab();
            sleep(1000);

            encoderDrive(-5);
            turnLeft(80);

            encoderDrive(20);
            //sleep(1000);

            turnRight(30);
            encoderDrive(30);

            turnLeft(30);
            encoderDrive(9);

            turnLeftAbsolute(90);



            //encoderDrive(-5);
            //turnLeft(5
            //encoderDrive(-30);

            madeTheRun = true;
        }

        printStatus();
    }
}

