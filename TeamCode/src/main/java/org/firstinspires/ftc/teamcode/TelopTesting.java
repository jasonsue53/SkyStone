package org.firstinspires.ftc.teamcode;

import android.hardware.Sensor;

//import com.acmerobotics.dashboard.FtcDashboard;
//import com.acmerobotics.dashboard.config.Config;
//import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.LED;
import com.qualcomm.robotcore.hardware.Servo;
//import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.util.ElapsedTime;

//@Config
@TeleOp(name = "Jason and Aurick Teleop", group = "Jason and Aurick Teleop Testing")
public class TelopTesting extends OpMode {

    // Variables we want to play with in dashboard


    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    // Chassis
    private DcMotor leftRearMotor = null;
    private DcMotor leftFrontMotor = null;
    private DcMotor rightRearMotor = null;
    private DcMotor rightFrontMotor = null;

    // Stone placement
    private DcMotor elevatorMotor = null;
    private DcMotorEx craneMotor = null;
    private DigitalChannel craneMagnet = null;
    private Servo handServo = null;

    // Stone sucker
    private DcMotor leftSuckerMotor = null;
    private DcMotor rightSuckerMotor = null;

    // Crab
    private Servo crabServo = null;

    // Cap Stone
    private Servo capServo = null;

    // LEDs
//    private LED ledLights = null;

    // State Variables
    private boolean g1startLastPos = false;
    private boolean g1xLastPos = false;
    private boolean g1lefttriggerLastPos = false;
    private boolean g2lefttriggerLastPos = false;

    private boolean driveReverse = true;

    private boolean elevatorAutomate = false;
    private boolean elevatorPhase1 = false;
    private boolean elevatorPhase2 = false;

    private boolean crabUp;

    private boolean craneDone = false;
    private int craneLastMagHit = 0;
    private int craneOffset = 0;
    private boolean craneMagLastState = true;
    private int craneMagStartFalse = 0;
    private int craneMagEndFalse = 0;

    // Constants
    private static final int ELEVATOR_BOTTOM = 0;
    private static final int ELEVATOR_PHASE1 = 800;
    private static final int ELEVATOR_FUDGE = 100;
    private static final double ELEVATOR_UP_POWER = 1.0;                                             // Speed we want elevator to move at when being manually controlled for fine tuning height
    private static final double ELEVATOR_DOWN_POWER = .5;
    private static final double ELEVATOR_HOLD_POWER = .001;
    private static final int ELEVATOR_HIGH = 3200;

    private static final double CRANE_POWER = 1;                                                    // Speed we want the crane to move
    private static final int CRANE_FUDGE = 30;
    private static final int CRANE_OUT = 860;
    private static final int CRANE_IN = 0;
    private static final int CRANE_MAGNET_POSITION = 110;
    private static final int CRANE_MAGNET_WIDTH = 30;

    private static final double CRAB_DOWN = 0.4;
    private static final double CRAB_UP = 0.65;

    private static final double HAND_IN = 1.0;
    private static final double HAND_OUT = 0.0;

    @Override
    public void init() {
        //telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        telemetry.addData("Status", "Initializing");

        // Chassis
        leftRearMotor = hardwareMap.get(DcMotor.class, "motorBackLeft");
        leftFrontMotor = hardwareMap.get(DcMotor.class, "motorFrontLeft");
        rightRearMotor = hardwareMap.get(DcMotor.class, "motorBackRight");
        rightFrontMotor = hardwareMap.get(DcMotor.class, "motorFrontRight");

        leftRearMotor.setDirection(DcMotor.Direction.REVERSE);
        leftFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        rightRearMotor.setDirection(DcMotor.Direction.FORWARD);
        rightFrontMotor.setDirection(DcMotor.Direction.FORWARD);

        //Elevator
        elevatorMotor = hardwareMap.get(DcMotor.class, "elevator");
        elevatorMotor.setDirection(DcMotor.Direction.REVERSE);
        elevatorMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevatorMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        elevatorMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Crane
        craneMotor = hardwareMap.get(DcMotorEx.class, "crane");
        craneMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        craneMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        craneMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        craneMagnet = hardwareMap.get(DigitalChannel.class, "crane_magnet");
        craneMagnet.setMode(DigitalChannel.Mode.INPUT);

        // Hand
        handServo = hardwareMap.get(Servo.class, "hand");
        handServo.setPosition(HAND_OUT);

        // Stone sucker
        leftSuckerMotor = hardwareMap.get(DcMotor.class, "left_sucker");
        leftSuckerMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightSuckerMotor = hardwareMap.get(DcMotor.class, "right_sucker");
        rightSuckerMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightSuckerMotor.setDirection(DcMotor.Direction.REVERSE);

        // Crab
        crabServo = hardwareMap.get(Servo.class, "crab");
        crabServo.setPosition(CRAB_UP);
        crabUp = true;

        // Cap Stone
        capServo = hardwareMap.get(Servo.class, "cap");
        capServo.setPosition(0.0);

        // LEDs
//        ledLights = hardwareMap.get(LED.class, "leds");
//        ledLights.

        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized COMPLETE");
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {

        /************************
         // **** UPDATE STATE VARIABLES
         ************************/
        int elevatorPosition = elevatorMotor.getCurrentPosition();                                 // Get current position and flip from negative to positive to make logic more readable
        int cranePosition = craneMotor.getCurrentPosition() + craneOffset;
        double elevatorPower = 0.0;                                                                 // Initialize to zero as the default power level and then determine below if we need to move
        double cranePower = 0.0;

        // Toggle front of robot
        if (gamepad1.start != g1startLastPos) {
            g1startLastPos = !g1startLastPos;
            if (gamepad1.start)
                driveReverse = !driveReverse;
        }


        boolean g1lefttriggerPressed = (gamepad1.left_trigger > 0.5);
        if (g1lefttriggerPressed != g1lefttriggerLastPos) {
            g1lefttriggerLastPos = !g1lefttriggerLastPos;
            if (g1lefttriggerPressed) {
                handServo.setPosition(HAND_OUT);
                elevatorAutomate = true;
                elevatorPhase1 = false;
                elevatorPhase2 = false;
                craneDone = false;
            }
        }

        boolean g2lefttriggerPressed = (gamepad2.left_trigger > 0.5);
        if (g2lefttriggerPressed != g2lefttriggerLastPos) {
            g2lefttriggerLastPos = !g2lefttriggerLastPos;
            if (g2lefttriggerPressed) {
                handServo.setPosition(HAND_OUT);
                elevatorAutomate = true;
                elevatorPhase1 = false;
                elevatorPhase2 = false;
                craneDone = false;
            }
        }

        if ((gamepad1.y || gamepad1.a) || (gamepad1.dpad_up || gamepad1.dpad_down) ||               // If there is any manual input cancel the automation
                (gamepad1.dpad_right || gamepad1.dpad_left)) {
            elevatorAutomate = false;
        }

        /************************
         **** AUTOMATION
         ************************/
        if (elevatorAutomate) {
            if (!elevatorPhase1 && elevatorPosition < ELEVATOR_PHASE1 - ELEVATOR_FUDGE)
                elevatorPower = ELEVATOR_UP_POWER;

            if (!elevatorPhase1 && elevatorPosition > ELEVATOR_PHASE1 + ELEVATOR_FUDGE)
                elevatorPower = -ELEVATOR_DOWN_POWER;

            if (elevatorPosition <= ELEVATOR_PHASE1 + ELEVATOR_FUDGE && elevatorPosition >= ELEVATOR_PHASE1 - ELEVATOR_FUDGE) {
                elevatorPower = 0;
                elevatorPhase1 = true;
            }

            if (elevatorPhase1 && craneDone && !elevatorPhase2)
                elevatorPower = -ELEVATOR_DOWN_POWER;

            if (!craneDone) {
                if (cranePosition > CRANE_FUDGE)
                    cranePower = -CRANE_POWER;
                if (cranePosition < -CRANE_FUDGE)
                    cranePower = CRANE_POWER;
            }

            if (cranePosition <= CRANE_FUDGE && cranePosition >= -CRANE_FUDGE) {
                cranePower = 0;
                craneDone = true;
            }

            if (elevatorPosition < 100 && craneDone)                                                                     // Once the phase2 of the elevator is done the automation is done
                elevatorAutomate = false;
        }

        /************************
         // **** DRIVE SYSTEM
         ************************/
        double driveForwardBack = gamepad1.left_stick_y;
        double driveStrafe = gamepad1.left_stick_x;
        double turn = -gamepad1.right_stick_x;

        // If in reverse mode flip the controller input for direction and strafe
        if (driveReverse) {
            driveForwardBack *= -1;
            driveStrafe *= -1;
        }

        double cap = 1.0f;
        double powerReductionFactor = 4.0f;
        double leftRearPower = Range.clip(driveForwardBack + turn + (driveStrafe), -cap, cap);
        double rightRearPower = Range.clip(driveForwardBack - turn - (driveStrafe), -cap, cap);
        double leftFrontPower = Range.clip(driveForwardBack + turn - driveStrafe, -cap, cap);
        double rightFrontPower = Range.clip(driveForwardBack - turn + driveStrafe, -cap, cap);

        // If right stick button is depressed move robot at quarter speed
        if (gamepad1.right_stick_button) {
            leftRearPower = leftRearPower / powerReductionFactor;
            rightRearPower = rightRearPower / powerReductionFactor;
            leftFrontPower = leftFrontPower / powerReductionFactor;
            rightFrontPower = rightFrontPower / powerReductionFactor;
        }

        // Set motor powers
        leftRearMotor.setPower(leftRearPower);
        rightRearMotor.setPower(rightRearPower);
        leftFrontMotor.setPower(leftFrontPower);
        rightFrontMotor.setPower(rightFrontPower);

        /************************
         // **** SUCKER *********
         ************************/
        double suckerPower = 0.0;
        if ((!gamepad1.right_bumper) && (gamepad1.right_trigger == 0)) {                            // If no input from controller 1 then look at controller 2
            // ---CONTROLLER 2
            suckerPower = gamepad2.right_trigger;
            if (gamepad2.right_bumper)
                suckerPower = -1;
        } else {
            suckerPower = gamepad1.right_trigger;
            if (gamepad1.right_bumper) {
                suckerPower = -1;
            }
        }

        rightSuckerMotor.setPower(suckerPower);
        leftSuckerMotor.setPower(suckerPower);

        /************************
         **** ELEVATOR
         ************************/
        boolean elevatorBottom = (elevatorPosition <= ELEVATOR_BOTTOM + ELEVATOR_FUDGE &&
                elevatorPosition >= ELEVATOR_BOTTOM - ELEVATOR_FUDGE);
        if ((!gamepad1.y) && (!gamepad1.a)) {                                                       // If no input from controller 1 then look at controller 2
            if (gamepad2.y)                                                                         // Operator 1's Y is pressed and we haven't reached our maximum height
                elevatorPower = ELEVATOR_UP_POWER;                                                  // Move up at a controllable speed
            else if (gamepad2.a && !elevatorBottom)                                                 // Operator 1's A is pressed and we haven't reached the bottom
                elevatorPower = -ELEVATOR_DOWN_POWER;                                               // Move down at a controllable speed
        } else {
            if (gamepad1.y)                                                                         // Operator 1's Y is pressed and we haven't reached our maximum height
                elevatorPower = ELEVATOR_UP_POWER;                                                  // Move up at a controllable speed
            else if (gamepad1.a && !elevatorBottom)                                                 // Operator 1's A is pressed and we haven't reached the bottom
                elevatorPower = -ELEVATOR_DOWN_POWER;                                               // Move down at a controllable speed
        }

        if (elevatorPosition > 200 && elevatorPower == 0)                                           // If the elevator is up and power is 0 we need to change it to a small up to make the motor hold
            elevatorPower = ELEVATOR_HOLD_POWER;

        elevatorMotor.setPower(elevatorPower);                                                      // Set direction and power determined above to the motor

        /************************
         **** CRANE
         ************************/
        boolean craneIn = (cranePosition >= CRANE_IN - CRANE_FUDGE &&
                cranePosition <= CRANE_IN + CRANE_FUDGE);
        boolean craneOut = (cranePosition >= CRANE_OUT - CRANE_FUDGE &&
                cranePosition <= CRANE_OUT + CRANE_FUDGE);
        if ((!gamepad1.dpad_down) && (!gamepad1.dpad_up)) {                                         // If no input from controller 1 then look at controller 2
            if (gamepad2.dpad_down && !craneIn)                                                     // Ignore input if crane is already all the way back
                cranePower = -CRANE_POWER;                                                          // Operator 1 is moving crane so move at slower speed for small adjustments

            if (gamepad2.dpad_up && !craneOut)                                                      // Ignore input if crane is already all the way out
                cranePower = CRANE_POWER;                                                           // Operator 1 is moving crane so move at slower speed for small adjustments
        } else {
            if (gamepad1.dpad_down && !craneIn)                                                     // Ignore input if crane is already all the way back
                cranePower = -CRANE_POWER;                                                          // Operator 1 is moving crane so move at slower speed for small adjustments

            if (gamepad1.dpad_up && !craneOut)                                                      // Ignore input if crane is already all the way out
                cranePower = CRANE_POWER;                                                           // Operator 1 is moving crane so move at slower speed for small adjustments
        }

        if (craneMagnet.getState() != craneMagLastState) {
            craneMagLastState = !craneMagLastState;
            craneLastMagHit = cranePosition;
            if (!craneMagLastState && cranePower > 0)
                craneOffset = (CRANE_MAGNET_POSITION - CRANE_MAGNET_WIDTH/2) -
                        craneMotor.getCurrentPosition();

            if (!craneMagLastState && cranePower < 0)
                craneOffset = (CRANE_MAGNET_POSITION + CRANE_MAGNET_WIDTH/2) -
                        craneMotor.getCurrentPosition();

            if (craneMagLastState == false)
                craneMagStartFalse = cranePosition;

            if (craneMagLastState == true)
                craneMagEndFalse = cranePosition;
        }

        craneMotor.setPower(cranePower);                                                            // Set power determined above to motor

        /************************
         **** HAND
         ************************/
        if ((!gamepad1.dpad_left) && (!gamepad1.dpad_right)) {                                      // If no input from controller 1 then look at controller 2
            if (gamepad2.dpad_left)
                handServo.setPosition(HAND_OUT);
            if (gamepad2.dpad_right)
                handServo.setPosition(HAND_IN);
        } else {
            if (gamepad1.dpad_left)
                handServo.setPosition(HAND_OUT);
            if (gamepad1.dpad_right)
                handServo.setPosition(HAND_IN);
        }

        /************************
         **** CRAB
         ************************/
        if (gamepad1.x != g1xLastPos) {
            g1xLastPos = !g1xLastPos;
            if (gamepad1.x)
                if (crabUp) {
                    crabServo.setPosition(CRAB_DOWN);
                    crabUp = false;
                } else {
                    crabServo.setPosition(CRAB_UP);
                    crabUp = true;
                }
        }

        /************************
         **** CAP STONE
         ************************/
        if (gamepad1.back)
            capServo.setPosition(0.5);

        /************************
         **** TELEMETRY
         ************************/
        telemetry.addData("Status", runtime.toString());
//        telemetry.addData("Front Motors", "Left (%.2f), Right (%.2f)", leftFrontPower, rightFrontPower);
//        telemetry.addData("Rear Motors", "Left (%.2f), Right (%.2f)", leftRearPower, rightRearPower);
//        telemetry.addData("Drive Reverse ", driveReverse);
        telemetry.addData("Elevator Height", elevatorPosition);
        telemetry.addData("Elevator Power", elevatorPower);
        telemetry.addData("Crane Position var", cranePosition);
        telemetry.addData("Crane Position cal", craneMotor.getCurrentPosition());

//        telemetry.addData("Elevator Phase 1", elevatorPhase1);
//        telemetry.addData("Elevator Phase 2", elevatorPhase2);
//        telemetry.addData("Elevator Automate", elevatorAutomate);
//        telemetry.addData("Crane Out", craneOut);
//        telemetry.addData("Crane In", craneIn);

//        telemetry.addData("Crane Magnet Position", craneLastMagHit);
        telemetry.addData("Crane Start of False", craneMagStartFalse);
        telemetry.addData("Crane End of False", craneMagEndFalse);
        telemetry.addData("Crane Offset", craneOffset);

    }

    @Override
    public void stop() {
    }
}


