/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package team498.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import team498.robot.commands.RampDrive;


public class Drivetrain extends Subsystem {	

    private static Drivetrain drivetrain = null;

    /**
     * Provides singleton access to the drivetrain subsystem
     * @return Drivetrain instance
     */
    public static Drivetrain getDrivetrain() {
        drivetrain = drivetrain == null ? new Drivetrain() : drivetrain;
        return drivetrain;
    }

    private DifferentialDrive drive = new DifferentialDrive(new Spark(2),new Spark(0));
    
	public Drivetrain(){
    	super("Drivetrain");
    }
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new RampDrive()); //uses rampdrive
	}
	
    public void drive(double move, double rotate) {

    	drive.arcadeDrive(move, rotate);
    	
/*        // Temporarily remmber the last values for the dashboard
        currentMove = move;
        currentRotate = rotate;

        // Apply motor power based on aracade inputs
        drive.arcadeDrive(move, rotate);
*/        
    }
    
}
