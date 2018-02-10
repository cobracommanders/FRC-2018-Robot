package team498.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import team498.robot.Mappings;

public class Vision extends Subsystem {
		
	private static Vision vision = null;
	public static Vision getVision() {
		vision = vision == null ? new Vision() : vision;
		return vision;
	}
	
	private UsbCamera camera1;
	private UsbCamera camera2;
	private int width1 = 150;
	private int height1 = 100;
	private int width2 = 150;
	private int height2 = 100;

	public void startCapture() { 
		camera1 = CameraServer.getInstance().startAutomaticCapture("camera1", Mappings.CameraDevice1);
		camera1.setResolution(width1, height1);
		camera2 = CameraServer.getInstance().startAutomaticCapture("camera2", Mappings.CameraDevice2);
		camera2.setResolution(width2, height2);
	}
	
    public void initDefaultCommand() {    	
    }
    
}

