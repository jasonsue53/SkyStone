package org.firstinspires.ftc.teamcode.autoOp;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.auto.ChassisStandard;
import org.firstinspires.ftc.teamcode.auto.ChassisConfig;

/**
 *
 */
@Autonomous(name="Short Blue Beanis 2", group="bluebeanis")
public class ShortBlueBeanis2 extends ChassisStandard {

    /**
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop () {

        if (madeTheRun == false) {

            encoderDrive(5);

            turnLeft(95);
            encoderDrive(35);
            madeTheRun = true;
        }

        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "time: " + runtime.toString());
        telemetry.addData("Gyro", "angle: " + this.getGyroscopeAngle());
        telemetry.addData("Status", "madeTheRun=%b", madeTheRun);
    }
}

