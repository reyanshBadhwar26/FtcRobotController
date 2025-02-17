package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous
public class AutoRedOutside extends LinearOpMode {

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

    @Override
    public void runOpMode() {
        rightCsf = hardwareMap.get(ColorSensor.class, "rightSensor");
        leftCsf = hardwareMap.get(ColorSensor.class, "sensor");
        RFm = hardwareMap.get(DcMotor.class, "RFm");
        LFm = hardwareMap.get(DcMotor.class, "LFm");
        RBm = hardwareMap.get(DcMotor.class, "RBm");
        LBm = hardwareMap.get(DcMotor.class, "LBm");
        leftClaw = hardwareMap.get(Servo.class, "leftClaw");
        rightClaw = hardwareMap.get(Servo.class, "rightClaw");
        claw = hardwareMap.get(Servo.class, "claw");
        ArmMotor = hardwareMap.get(DcMotorEx.class, "ArmMotor");
        ArmMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftClaw.setPosition(0.5);
        rightClaw.setPosition(1);

        waitForStart();
        while (opModeIsActive()) {
            //Moving Forward to get a closer look at the prop
            claw.setPosition(0.9);
            ArmMotor.setTargetPosition(-400);
            ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            ArmMotor.setPower(-0.5);
            ArmMotor.setVelocity(800);
            sleep(1000);
            RFm.setPower(1);
            LFm.setPower(-1);
            RBm.setPower(1);
            LBm.setPower(-1);
            sleep (1300);
            RFm.setPower(0);
            LFm.setPower(0);
            RBm.setPower(0);
            LBm.setPower(0);
            sleep (1000);
            //Check Right First
            boolean foundRight = false;
            for (int i = 0; i < 50; i ++){
                if (rightCsf.red() > rightCsf.green() && rightCsf.red() > rightCsf.blue()){
                    foundRight = true;
                }
            }
            if (foundRight) {
                RFm.setPower(-1);
                LFm.setPower(-1);
                RBm.setPower(-1);
                LBm.setPower(-1);
                sleep (730);
                RFm.setPower(0);
                LFm.setPower(0);
                RBm.setPower(0);
                LBm.setPower(0);
                sleep(1000);
                RFm.setPower(-1);
                LFm.setPower(1);
                RBm.setPower(-1);
                LBm.setPower(1);
                sleep(300);
                RFm.setPower(0);
                LFm.setPower(0);
                RBm.setPower(0);
                LBm.setPower(0);
                sleep(1000);
                RFm.setPower(-1);
                LFm.setPower(-1);
                RBm.setPower(-1);
                LBm.setPower(-1);
                sleep (100);
                RFm.setPower(0);
                LFm.setPower(0);
                RBm.setPower(0);
                LBm.setPower(0);
                sleep(1000);
                claw.setPosition(0.9);
                sleep(1000);
                ArmMotor.setTargetPosition(-250);
                ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                ArmMotor.setPower(0.5);
                sleep(500);
                RFm.setPower(1);
                LFm.setPower(-1);
                RBm.setPower(1);
                LBm.setPower(-1);
                sleep(100);
                RFm.setPower(0);
                LFm.setPower(0);
                RBm.setPower(0);
                LBm.setPower(0);
                sleep(1000);
                ArmMotor.setTargetPosition(0);
                ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                ArmMotor.setPower(0.5);
                sleep(500);
                leftClaw.setPosition(1);
                sleep(1000);
                claw.setPosition(-0.7);
                //THE CODE FOR PUTTING THE PIXEL STOPS HERE
                break;
            } //If Did not find right, Check Front
            else{
                boolean foundFront = false;
                for (int i = 0; i < 50; i ++){
                    if (leftCsf.red() > leftCsf.green() && leftCsf.red() > leftCsf.blue()){
                        foundFront = true;
                    }
                }
                if (foundFront){
                    RFm.setPower(-1);
                    LFm.setPower(1);
                    RBm.setPower(-1);
                    LBm.setPower(1);
                    sleep(300);
                    RFm.setPower(0);
                    LFm.setPower(0);
                    RBm.setPower(0);
                    LBm.setPower(0);
                    sleep(1000);
                    ArmMotor.setTargetPosition(-250);
                    ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    ArmMotor.setPower(0.5);
                    sleep(500);
                    claw.setPosition(0.9);
                    sleep(1000);
                    RFm.setPower(1);
                    LFm.setPower(-1);
                    RBm.setPower(1);
                    LBm.setPower(-1);
                    sleep(90);
                    RFm.setPower(0);
                    LFm.setPower(0);
                    RBm.setPower(0);
                    LBm.setPower(0);
                    sleep(1000);
                    ArmMotor.setTargetPosition(0);
                    ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    ArmMotor.setPower(0.5);
                    sleep(500);
                    leftClaw.setPosition(1);
                    sleep(1000);
                    claw.setPosition(-0.7);
                    //THE CODE FOR PUTTING THE PIXEL STOPS HERE
                    break;
                } //If did not find either right or front, just put it on left man
                else {
                    RFm.setPower(1);
                    LFm.setPower(1);
                    RBm.setPower(1);
                    LBm.setPower(1);
                    sleep (700);
                    RFm.setPower(0);
                    LFm.setPower(0);
                    RBm.setPower(0);
                    LBm.setPower(0);
                    sleep(1000);
                    RFm.setPower(-1);
                    LFm.setPower(1);
                    RBm.setPower(-1);
                    LBm.setPower(1);
                    sleep(50);
                    RFm.setPower(0);
                    LFm.setPower(0);
                    RBm.setPower(0);
                    LBm.setPower(0);
                    sleep(1000);
                    RFm.setPower(1);
                    LFm.setPower(-1);
                    RBm.setPower(1);
                    LBm.setPower(-1);
                    sleep(30);
                    RFm.setPower(0);
                    LFm.setPower(0);
                    RBm.setPower(0);
                    LBm.setPower(0);
                    sleep(1000);
                    ArmMotor.setTargetPosition(0);
                    ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    ArmMotor.setPower(0.5);
                    sleep(500);
                    claw.setPosition(0.7);
                    sleep(1000);
                    leftClaw.setPosition(1);
                    sleep(1000);
                    claw.setPosition(-0.7);
                    //THE CODE FOR PUTTING THE PIXEL STOPS HERE
                    break;
                }
            }
        }
    }
}
