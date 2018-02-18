package team498.robot.dynamic;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Represents a joystick input, can be an axis or a button, your choice.<br/>
 * Made in a similar fashion to JoystickButton, but with axis support.
 * 
 * @version 1.0
 * 
 * @author Micah Neitz <br/>
 *         Team 498
 */
public final class JoystickInput {

	Joystick stick;
	int port;
	boolean isButton;

	/**
	 * Makes the input, uses the {@link Joystick} and port to decide where to get
	 * values from. It doesn't know whether you want an axis or a button, so it
	 * needs you to specify
	 * 
	 * @param stick
	 *            The {@link Joystick} to gran the values from
	 * @param port
	 *            The port to read from the {@link Joystick}
	 * @param isButton
	 *            Determines if the input is an axis or a button
	 */
	public JoystickInput(Joystick stick, int port, boolean isButton) {
		this.stick = stick;
		this.port = port;
		this.isButton = isButton;
	}

	/**
	 * Returns the axis of the joystick, or {@code 0.0} if the joystick is meant to
	 * be a button
	 * 
	 * @return The axis value
	 */
	public double GetAxis() {
		return isButton ? 0.0 : stick.getRawAxis(port);
	}

	/**
	 * Returns the value of the button, or {@code false} if it's meant to be an axis
	 * 
	 * @return The button value
	 */
	public boolean GetButton() {
		return isButton ? stick.getRawButton(port) : false;
	}
}
