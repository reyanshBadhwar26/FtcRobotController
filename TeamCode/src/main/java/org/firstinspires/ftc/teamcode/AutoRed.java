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
public class AutoRed extends LinearOpMode {

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

    public void shuffleLeft(int pos){
        LFm.setPower(-0.5);
        LBm.setPower(0.5);
        RFm.setPower(0.5);
        RBm.setPower(-0.5);

        LFm.setTargetPosition(LFm.getCurrentPosition() - pos);
        LBm.setTargetPosition(LBm.getCurrentPosition() + pos);
        RFm.setTargetPosition(RFm.getCurrentPosition() + pos);
        RBm.setTargetPosition(RBm.getCurrentPosition() - pos);

        LFm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LBm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RFm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RBm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void shuffleRight(int pos){
        LFm.setPower(0.5);
        LBm.setPower(-0.5);
        RFm.setPower(-0.5);
        RBm.setPower(0.5);

        LFm.setTargetPosition(LFm.getCurrentPosition() + pos);
        LBm.setTargetPosition(LBm.getCurrentPosition() -pos);
        RFm.setTargetPosition(RFm.getCurrentPosition() -pos);
        RBm.setTargetPosition(RBm.getCurrentPosition()+ pos);

        LFm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LBm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RFm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RBm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void moveForward(int pos){
        RFm.setPower(1);
        LFm.setPower(1);
        RBm.setPower(1);
        LBm.setPower(1);

        LFm.setTargetPosition(LFm.getCurrentPosition() + pos);
        LBm.setTargetPosition(LBm.getCurrentPosition() + pos);
        RFm.setTargetPosition(RFm.getCurrentPosition() + pos);
        RBm.setTargetPosition(RBm.getCurrentPosition() + pos);

        LFm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LBm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RFm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RBm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void moveBackward(int pos){
        RFm.setPower(-1);
        LFm.setPower(-1);
        RBm.setPower(-1);
        LBm.setPower(-1);

        LFm.setTargetPosition(LFm.getCurrentPosition() -pos);
        LBm.setTargetPosition(LBm.getCurrentPosition() -pos);
        RFm.setTargetPosition(RFm.getCurrentPosition() -pos);
        RBm.setTargetPosition(RBm.getCurrentPosition() -pos);

        LFm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LBm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RFm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RBm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void stop(int ms){
        RFm.setPower(0);
        LFm.setPower(0);
        RBm.setPower(0);
        LBm.setPower(0);
        sleep (ms);
    }

    public void steerRight(int pos){
        RFm.setPower(-1);
        LFm.setPower(1);
        RBm.setPower(-1);
        LBm.setPower(1);

        LFm.setTargetPosition(LFm.getCurrentPosition() + pos);
        LBm.setTargetPosition(LBm.getCurrentPosition() + pos);
        RFm.setTargetPosition(RFm.getCurrentPosition() -pos);
        RBm.setTargetPosition(RBm.getCurrentPosition() -pos);

        LFm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LBm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RFm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RBm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void steerLeft(int pos){
        RFm.setPower(1);
        LFm.setPower(-1);
        RBm.setPower(1);
        LBm.setPower(-1);

        LFm.setTargetPosition(LFm.getCurrentPosition() -pos);
        LBm.setTargetPosition(LBm.getCurrentPosition() -pos);
        RFm.setTargetPosition(RFm.getCurrentPosition() + pos);
        RBm.setTargetPosition(RBm.getCurrentPosition() + pos);

        LFm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LBm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RFm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RBm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    @Override
    public void runOpMode() {

        rightCsf = hardwareMap.get(ColorSensor.class, "rightSensor");
        leftCsf = hardwareMap.get(ColorSensor.class, "leftSensor");

        RFm = hardwareMap.get(DcMotorEx.class, "RFm");
        LFm = hardwareMap.get(DcMotorEx.class, "LFm");
        RBm = hardwareMap.get(DcMotorEx.class, "RBm");
        LBm = hardwareMap.get(DcMotorEx.class, "LBm");

        RBm.setDirection(DcMotor.Direction.REVERSE);
        RFm.setDirection(DcMotor.Direction.REVERSE);

        RFm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RBm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LBm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LFm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftClaw = hardwareMap.get(Servo.class, "leftClaw");
        rightClaw = hardwareMap.get(Servo.class, "rightClaw");
        claw = hardwareMap.get(Servo.class, "claw");

        ArmMotor = hardwareMap.get(DcMotorEx.class, "ArmMotor");
        ArmMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftClaw.setPosition(0.5);
        rightClaw.setPosition(1);
        sleep(100);
        claw.setPosition(-0.7);

        waitForStart();
        while (opModeIsActive()) {
            //Moving Forward to get a closer look at the prop
            moveForward(960);
            stop(300);
            shuffleRight(480);
            //CheckRight First
            boolean foundRight = false;
            for (int i = 0; i < 50; i ++){
                if (rightCsf.red() > rightCsf.green() && rightCsf.red() > rightCsf.blue()){
                    foundRight = true;
                }
            }
            moveForward(100);
            stop(300);
            for (int i = 0; i < 50; i ++){
                if (rightCsf.red() > rightCsf.green() && rightCsf.red() > rightCsf.blue()){
                    foundRight = true;
                }
            }
            moveBackward(100);
            stop(300);
            for (int i = 0; i < 50; i ++){
                if (rightCsf.red() > rightCsf.green() && rightCsf.red() > rightCsf.blue()){
                    foundRight = true;
                }
            }
            if (foundRight) {
                shuffleLeft(480);
                stop(300);
                steerRight(500);
                moveForward(240);
                stop(300);
                claw.setPosition(0.7);
                leftClaw.setPosition(1);
                sleep(300);
                //THE CODE FOR PUTTING THE PIXEL STOPS HERE
                break;
            } //If Did not find right, Check Left
            else{
                shuffleLeft(960);
                stop(500);
                boolean foundLeft = false;
                for (int i = 0; i < 50; i ++){
                    if (leftCsf.red() > leftCsf.green() && leftCsf.red() > leftCsf.blue()){
                        foundLeft = true;
                    }
                }
                moveForward(100);
                stop(300);
                for (int i = 0; i < 50; i ++){
                    if (leftCsf.red() > leftCsf.green() && leftCsf.red() > leftCsf.blue()){
                        foundLeft = true;
                    }
                }
                moveBackward(100);
                stop(300);
                for (int i = 0; i < 50; i ++){
                    if (leftCsf.red() > leftCsf.green() && leftCsf.red() > leftCsf.blue()){
                        foundLeft = true;
                    }
                }
                if (foundLeft){
                    shuffleRight(480);
                    stop(300);
                    steerLeft(480);
                    stop(300);
                    moveForward(480);
                    stop(300);
                    claw.setPosition(0.7);
                    leftClaw.setPosition(1);
                    sleep(1000);
                    //THE CODE FOR PUTTING THE PIXEL STOPS HERE
                    break;
                } //If did not find either right or front, just put it on front man
                else {
                    shuffleRight(480);
                    moveForward(480);
                    stop(1000);
                    claw.setPosition(0.7);
                    leftClaw.setPosition(1);
                    sleep(1000);
                    //THE CODE FOR PUTTING THE PIXEL STOPS HERE
                    break;
                }
            }
        }
    }
}
