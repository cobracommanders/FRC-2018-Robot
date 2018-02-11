package team498.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team498.robot.Mappings;

public class Vision extends Subsystem {
		
	private static Vision vision = null;
	public static Vision getVision() {
		vision = vision == null ? new Vision() : vision;
		return vision;
	}
	
	private UsbCamera camera1;
	private int width1 = 150;
	private int height1 = 100;
	
	private UsbCamera camera2;	
	private int width2 = 150;
	private int height2 = 100;

	public void startCapture() { 
		camera1 = CameraServer.getInstance().startAutomaticCapture("camera1", Mappings.CameraDevice1);
		camera1.setResolution(width1, height1);
		camera1.setFPS(30);
		if (camera1.isConnected() == false) {
			camera1.free();
		}
		
		camera2 = CameraServer.getInstance().startAutomaticCapture("camera2", Mappings.CameraDevice2);
		camera2.setResolution(width2, height2);
		camera2.setFPS(30);
		if (camera2.isConnected() == false) {
			camera2.free();
		}
		
		//SmartDashboard.putString("Camera connections", String.format("Camera 1: %s\nCamera 2: %s", camera1.isConnected(), camera2.isConnected()));
	}
	
    public void initDefaultCommand() {    	
    }    
}

