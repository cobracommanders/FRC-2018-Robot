/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package team498.robot;

import java.io.IOException;
import java.util.List;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team498.robot.commands.Auto;
import team498.robot.subsystems.Drivetrain;
import team498.robot.subsystems.Gyro;
import team498.robot.subsystems.Vision;
import team498.robot.dynamicAuto.*;

public class Robot extends TimedRobot {

    private Operator operator = Operator.getOperator();
    private final String autoTest = "/home/lvuser/frc/dynamicauto/test5.txt";
    private DynamicAutoRecorder dar;
    // Subsystems
    private Drivetrain drivetrain = Drivetrain.getDrivetrain();
    private Vision vision = Vision.getVision();
    private Gyro gyro = Gyro.getGyro();

    private boolean recorded = false;

    private Timer timer;

    private Auto auto = new Auto();

    @Override
    public void robotInit() {
        vision.startCapture();
        dar = DynamicAutoRecorder.getAutoRecorder();
        updateDashboard();
    }

    @Override
    public void disabledInit() {
    }

    @Override
    public void disabledPeriodic() {
        updateDashboard();
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        updateDashboard();
        DynamicCommand dc = null;
        if (dar != null) {
            try {
                dc = dar.CreateDynamic(autoTest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (dc != null) {
            auto.addSequential(dc, 15);
        }
        this.auto.start();
    }

    @Override
    public void autonomousPeriodic() {
        updateDashboard();
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        updateDashboard();
        this.auto.cancel();
        dar = DynamicAutoRecorder.getAutoRecorder();
        dar.StartRecording();
        timer = new Timer();
        timer.start();
    }

    @Override
    public void teleopPeriodic() {
        updateDashboard();
        List<String> tags;
        try {
            if (timer.get() < 15) {
                tags = dar.buttonRec.detect();
                System.out.println("After Detect " + tags.size());

                for (int i = 0; i < tags.size(); i++) {
                    System.out.println(tags.get(i));
                    dar.buttonChange(tags.get(i));
                }
                System.out.println("Detecting...");
            } else if (!recorded) {
                recorded = true;
                dar.StopRecording(autoTest);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            e.printStackTrace();
        }
        Scheduler.getInstance().run();
    }


    @Override
    public void testPeriodic() {
    }

    void updateDashboard() {
        operator.updateDashboard();
        gyro.updateDashboard();
        //TODO add other subsystems
    }
}
