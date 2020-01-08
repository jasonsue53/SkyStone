package org.firstinspires.ftc.teamcode.autoOp;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.auto.ChassisStandard;

/**
 *
 */
@Autonomous(name="FoundationSnatchRedBridge", group="OpMode")
public class FoundationSnatchRedBridge extends ChassisStandard {

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
            turnRight(90);
            encoderDrive(9);
            dropCrab();
            sleep(1000);
            turnRight(65);
            encoderDrive(22);

            raiseCrab();
            sleep(1000);

            encoderDrive(-5);
            turnRight(80);

            encoderDrive(30);
            sleep(1000);

            turnRight(30);
            encoderDrive(15);



            //encoderDrive(-5);
            //turnLeft(55);
            //encoderDrive(-30);

            madeTheRun = true;
        }

        printStatus();
    }
}

