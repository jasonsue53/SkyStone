package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.auto.ChassisStandard;

//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//import com.qualcomm.robotcore.hardware.DistanceSensor;

@TeleOp(name="Manually Calibrate", group="Jason and Aurick Teleop Testing")
public class ManuallyCalibrate extends TylerController
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    // Stone placement
    private DcMotor elevatorMotor = null;
    private DcMotor craneMotor = null;
    private CRServo handServo = null;


    @Override
    public void init() {
        telemetry.addData("Status", "Initializing");

        //Stone placement
        elevatorMotor = hardwareMap.get(DcMotor.class, "elevator");
        elevatorMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        craneMotor = hardwareMap.get(DcMotor.class, "crane");
        craneMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        handServo = hardwareMap.get(CRServo.class, "hand");

        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
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

        // *** ELEVATOR ***
        double elevatorPowerFactor = 1.0;
        double elevatorPower = 0.0;
        if (gamepad1.y)
            elevatorPower = -elevatorPowerFactor;
        if (gamepad1.a)
            elevatorPower = elevatorPowerFactor;
        elevatorMotor.setPower(elevatorPower);

        // *** CRANE ***
        double cranePowerFactor = 0.5;
        double cranePower = 0.0;
        if (gamepad1.dpad_down) {
            cranePower = -cranePowerFactor;
        }
        if (gamepad1.dpad_up) {
            cranePower = cranePowerFactor;
        }
        craneMotor.setPower(cranePower);

        // *** HAND ***
        double handPowerFactor = 1.0;
        double handPower = 0.0;
        if (gamepad1.dpad_left) {
            handPower = -1.0 * handPowerFactor;
        }
        if (gamepad1.dpad_right) {
            handPower = 1.0 * handPowerFactor;
        }
        handServo.setPower(handPower);


        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Elevator Height", "%7d",
                elevatorMotor.getCurrentPosition());
    }

    @Override
    public void stop() {
    }
}


