package team498.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;


public class Vision extends Subsystem {
	private UsbCamera camera;
   
	public void vision() { 
		camera = CameraServer.getInstance().startAutomaticCapture("cam0", 0); //camera works! BABYYYYY
	}
	
    public void initDefaultCommand() {
        
    		
    		
    }
}

