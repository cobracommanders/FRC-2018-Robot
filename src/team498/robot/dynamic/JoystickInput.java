package team498.robot.dynamic;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Represents a joystick input, can be an axis or a button, your choice.<br/>
 * Made in a similar fashion to JoystickButton, but with axis support
 * 
 * @version 1.0
 * 
 * @author Micah Neitz <br/>
 *         Team 498
 */
public class JoystickInput {
	Joystick stick;
	int port;
	boolean isButton;

	public JoystickInput(Joystick stick, int port, boolean isButton) {
		this.stick = stick;
		this.port = port;
		this.isButton = isButton;
	}

	public double GetAxis() {
		return isButton ? 0.0 : stick.getRawAxis(port);
	}

	public boolean GetButton() {
		return isButton ? stick.getRawButton(port) : false;
	}
}
