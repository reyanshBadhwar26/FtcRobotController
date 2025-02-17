package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.Gyroscope;

@TeleOp

public class FullTeleOp extends LinearOpMode{

    private DcMotor LBm;
    private DcMotor LFm;
    private DcMotor RBm;
    private DcMotor RFm;
    private DcMotorEx ArmMotor;
    //private DcMotorEx ArmString;
    private Servo leftClaw;
    private Servo rightClaw;
    private Servo clawMov;
    private Servo drone;

    private int homeArmPosition;
    private int targetPosition;
    private int count;

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

    public void runOpMode(){
        //WheelsInit
        LBm = hardwareMap.get(DcMotor.class, "LBm");
        LFm = hardwareMap.get(DcMotor.class, "LFm");
        RBm = hardwareMap.get(DcMotor.class, "RBm");
        RFm = hardwareMap.get(DcMotor.class, "RFm");
        RBm.setDirection(DcMotor.Direction.REVERSE);
        RFm.setDirection(DcMotor.Direction.REVERSE);
        //ArmInit
        ArmMotor = hardwareMap.get(DcMotorEx.class, "ArmMotor");
        ArmMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //All ServosInit
        leftClaw = hardwareMap.get(Servo.class, "leftClaw");
        rightClaw = hardwareMap.get(Servo.class, "rightClaw");
        drone = hardwareMap.get(Servo.class, "drone");
        clawMov = hardwareMap.get(Servo.class, "claw");

        homeArmPosition = 0;
        targetPosition = 0;
        //count = 0;

        //initializeClaw
        leftClaw.setPosition(0.5);
        rightClaw.setPosition(1);
        sleep(100);
        clawMov.setPosition(-0.7);



        waitForStart();

        while (opModeIsActive()){

            double LS = gamepad1.left_stick_y;
            double RS = -gamepad1.right_stick_y;
            boolean pickPixel = gamepad1.a;
            boolean dropPixel = gamepad1.y;

            // The Code below is for the movement of wheels
            if (gamepad1.dpad_left) {
                shuffleLeft(10);
            } else if (gamepad1.dpad_right) {
                shuffleRight(10);
            } else{
                LFm.setPower(-LS) ;
                LBm.setPower(-LS);
                RFm.setPower(RS);
                RBm.setPower(RS);
            }

            // Code for Arm Starts Here
            if (dropPixel){
                targetPosition = -1400;
                ArmMotor.setTargetPosition(targetPosition);
                ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                ArmMotor.setPower(-0.5);
                ArmMotor.setVelocity(800);
            } else if (pickPixel){
                ArmMotor.setTargetPosition(homeArmPosition);
                ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                ArmMotor.setVelocity(1000);
            }


            //Code for Claw Starts Here

            //CloseClaw
            if(gamepad1.x){
                leftClaw.setPosition(1);
                rightClaw.setPosition(0.4);
            }
            //OpenClaw
            else if(gamepad1.b){
                leftClaw.setPosition(0.5);
                rightClaw.setPosition(1);
            }

            //Code for moving the claw up/down

            //Move up
            if (gamepad1.dpad_up){
                clawMov.setPosition(-1);
            }
            //Move down
            else if (gamepad1.dpad_down ){
                clawMov.setPosition(0.7);
            }

            //This Code is for the plane drone
            //Start Button chosen to launch the plane because it is the hardest to hit by accident
            if(gamepad1.options){
                drone.setPosition(0.5);
            }
            else if (gamepad1.left_bumper){
                drone.setPosition(-1);
            }

        }
    }


}