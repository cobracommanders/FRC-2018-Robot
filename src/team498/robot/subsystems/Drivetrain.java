/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package team498.robot.subsystems;

//import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import team498.robot.commands.Drive;

//import edu.wpi.first.wpilibj.PIDOutput;
//import edu.wpi.first.wpilibj.PIDSource;
//import edu.wpi.first.wpilibj.PIDSourceType;


public class Drivetrain extends Subsystem {	
	
	private double moveValue = 0;
	
	private Victor victor0 = new Victor(0);
	private Victor victor1 = new Victor(1);

    private static Drivetrain drivetrain = null;
    //private Gyro gyro = null;
    /**
     * Provides singleton access to the drivetrain subsystem
     * @return Drivetrain instance
     */
    //private PIDSource pidSource = new PIDSource() {
/*		
		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
			// TODO Auto-generated method stub
		}
		
		@Override
		public double pidGet() {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public PIDSourceType getPIDSourceType() {
			// TODO Auto-generated method stub
			return null;
		}
	};
	private PIDOutput pidOutput = new PIDOutput() {
		
		@Override
		public void pidWrite(double output) {
			// TODO Auto-generated method stub
			
		}
	};
	
    private PIDController rampController = new PIDController(0.2,0,0,pidSource,pidOutput);
    */
    
    public static Drivetrain getDrivetrain() {
        drivetrain = drivetrain == null ? new Drivetrain() : drivetrain;
        return drivetrain;
    }
    private DifferentialDrive drive = new DifferentialDrive(victor1, victor0);
    
	public Drivetrain(){
    	super("Drivetrain");
    	/*rampController.setContinuous(true);
    	rampController.setInputRange(-1, 1);
    	rampController.setOutputRange(-1, 1);
    	rampController.enable();*/
    	
    }
	

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new Drive());
	}
	public double getMoveValue() {
		return moveValue;
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
    
    public void pidWrite(double output) {
    	this.victor0.pidWrite(output);
    	this.victor1.pidWrite(output);
    }
   /* public void pidDrive(double move, double rotate) {
    	
    	//rampController.setSetpoint(move);
    	
    	
    }*/
	
}
