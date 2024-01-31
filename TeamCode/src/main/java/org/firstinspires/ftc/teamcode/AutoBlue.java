package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous
public class AutoBlue extends LinearOpMode {

    private ColorSensor rightCsf;
    private ColorSensor leftCsf;
    private DcMotor RFm;
    private DcMotor LFm;
    private DcMotor RBm;
    private DcMotor LBm;
    private Servo leftClaw;
    private Servo rightClaw;
    private Servo claw;
    private DcMotorEx ArmMotor;

    public void shuffleLeft(int ms){
        LFm.setPower(-0.5);
        LBm.setPower(0.5);
        RFm.setPower(0.5);
        RBm.setPower(-0.5);
        sleep(ms);
    }

    public void shuffleRight(int ms){
        LFm.setPower(0.5);
        LBm.setPower(-0.5);
        RFm.setPower(-0.5);
        RBm.setPower(0.5);
        sleep(ms);
    }

    public void moveForward(int ms){
        RFm.setPower(1);
        LFm.setPower(1);
        RBm.setPower(1);
        LBm.setPower(1);
        sleep (ms);
    }

    public void moveBackward(int ms){
        RFm.setPower(-1);
        LFm.setPower(-1);
        RBm.setPower(-1);
        LBm.setPower(-1);
        sleep (ms);
    }

    public void stop(int ms){
        RFm.setPower(0);
        LFm.setPower(0);
        RBm.setPower(0);
        LBm.setPower(0);
        sleep (ms);
    }

    public void steerRight(int ms){
        RFm.setPower(-1);
        LFm.setPower(1);
        RBm.setPower(-1);
        LBm.setPower(1);
        sleep(ms);
    }

    public void steerLeft(int ms){
        RFm.setPower(1);
        LFm.setPower(-1);
        RBm.setPower(1);
        LBm.setPower(-1);
        sleep(ms);
    }
    @Override
    public void runOpMode() {
        rightCsf = hardwareMap.get(ColorSensor.class, "rightSensor");
        leftCsf = hardwareMap.get(ColorSensor.class, "sensor");
        RFm = hardwareMap.get(DcMotor.class, "RFm");
        LFm = hardwareMap.get(DcMotor.class, "LFm");
        RBm = hardwareMap.get(DcMotor.class, "RBm");
        LBm = hardwareMap.get(DcMotor.class, "LBm");
        RBm.setDirection(DcMotor.Direction.REVERSE);
        RFm.setDirection(DcMotor.Direction.REVERSE);
        leftClaw = hardwareMap.get(Servo.class, "leftClaw");
        rightClaw = hardwareMap.get(Servo.class, "rightClaw");
        claw = hardwareMap.get(Servo.class, "claw");
        ArmMotor = hardwareMap.get(DcMotorEx.class, "ArmMotor");
        ArmMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        claw.setPosition(-1);
        leftClaw.setPosition(0.5);
        rightClaw.setPosition(1);

        waitForStart();
        while (opModeIsActive()) {
            //Moving Forward to get a closer look at the prop
            moveForward(1000);
            stop(1000);
            shuffleRight(200);
            //CheckRight First
            boolean foundRight = false;
            for (int i = 0; i < 50; i ++){
                if (rightCsf.blue() > rightCsf.green() && rightCsf.blue() > rightCsf.red()){
                    foundRight = true;
                }
            }
            if (foundRight) {
                shuffleLeft(200);
                stop(1000);
                steerRight(500);
                stop(1000);
//                claw.setPosition(0.9);
//                sleep(1000);
//                ArmMotor.setTargetPosition(-250);
//                ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                ArmMotor.setPower(0.5);
//                sleep(500);
                moveForward(200);
                stop(1000);
//                ArmMotor.setTargetPosition(0);
//                ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                ArmMotor.setPower(0.5);
//                sleep(500);
//                leftClaw.setPosition(1);
//                sleep(1000);
//                claw.setPosition(-0.7);
                //THE CODE FOR PUTTING THE PIXEL STOPS HERE
                break;
            } //If Did not find right, Check Left
            else{
                shuffleLeft(400);
                stop(500);
                boolean foundLeft = false;
                for (int i = 0; i < 50; i ++){
                    if (leftCsf.blue() > leftCsf.green() && leftCsf.blue() > leftCsf.red()){
                        foundLeft = true;
                    }
                }
                if (foundLeft){
                    shuffleRight(200);
                    stop(1000);
//                    ArmMotor.setTargetPosition(-150);
////                    ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
////                    ArmMotor.setPower(0.5);
////                    sleep(500);
////                    claw.setPosition(0.5);
////                    sleep(1000);
                    steerLeft(500);
                    stop(1000);
                    moveForward(500);
                    stop(1000);
//                    ArmMotor.setTargetPosition(0);
//                    ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                    ArmMotor.setPower(0.5);
//                    sleep(500);
//                    leftClaw.setPosition(1);
//                    sleep(1000);
//                    claw.setPosition(-0.7);
                    //THE CODE FOR PUTTING THE PIXEL STOPS HERE
                    break;
                } //If did not find either right or front, just put it on front man
                else {
                    moveBackward(100);
                    stop(1000);
//                    ArmMotor.setTargetPosition(0);
//                    ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                    ArmMotor.setPower(0.5);
//                    sleep(500);
//                    claw.setPosition(0.7);
//                    sleep(1000);
//                    leftClaw.setPosition(1);
//                    sleep(1000);
//                    claw.setPosition(-0.7);
                    moveForward(300);
                    //THE CODE FOR PUTTING THE PIXEL STOPS HERE
                    break;
                }
            }
        }
    }
}
