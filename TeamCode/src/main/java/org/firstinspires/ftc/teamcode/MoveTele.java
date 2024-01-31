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

public class MoveTele extends LinearOpMode{

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

        homeArmPosition = 0;
        targetPosition = 0;
        //count = 0;

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

        }
    }


}