package team498.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import team498.robot.Mappings;

public class Vision extends Subsystem {

	public static final int Width = 150;
	public static final int Height = 100;

	private static Vision vision = null;

	public static Vision getVision() {
		vision = vision == null ? new Vision() : vision;
		return vision;
	}

	private UsbCamera camera;

	public void startCapture() {
		camera = CameraServer.getInstance().startAutomaticCapture("camera", Mappings.CameraDevice);
		camera.setResolution(Width, Height);
	}

	public void initDefaultCommand() {

	}

}
