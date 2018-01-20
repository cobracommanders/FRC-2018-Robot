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
	
	private UsbCamera camera;
	private int width = 480;
	private int height = 360;
	
	public void startCapture() { 
		camera = CameraServer.getInstance().startAutomaticCapture("camera", Mappings.CameraDevice);
		camera.setResolution(width, height);
	}
	
    public void initDefaultCommand() {
    	
    }
    
}

