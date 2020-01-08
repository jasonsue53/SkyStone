package org.firstinspires.ftc.teamcode.autoOpTest;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.auto.ChassisConfig;
import org.firstinspires.ftc.teamcode.auto.ChassisStandard;

import java.util.List;

/**
 *
 */
@Autonomous(name="Stone Recog Test", group="ZZTesting")
public class StoneRecognitionTest extends ChassisStandard {

    int numMoved = 0;

    public StoneRecognitionTest() {
        useVuforia = true;  // Override the default of Vuforia being off.
    }

    @Override
    public void loop () {

        // Scan in place for a second.
        int count = 0;
        while (stoneconfig.equals(UNKNOWN) && (count < 2)){
            scanStones();
            printStatus();
            sleep(500);
            count++;
        }

        // Move forward, and scan again on the next loop.
        if (numMoved < 1) {
            encoderDrive(4);
            numMoved++;
            sleep(1000); // TODO: consider making this smaller
            return;
        }

        scanStones();
        printStatus();
    }
}
