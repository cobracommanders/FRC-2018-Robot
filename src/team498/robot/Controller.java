package team498.robot;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class Controller {
	private Joystick joystick;

	public JoystickButton buttonA;
	public JoystickButton buttonB;
	public JoystickButton buttonX;
	public JoystickButton buttonY;
	public JoystickButton buttonLeftBumper;
	public JoystickButton buttonRightBumper;

	public JoystickAxis axisLeftX;
	public JoystickAxis axisRightX;
	public JoystickAxis axisLeftY;
	public JoystickAxis axisRightY;
	public JoystickAxis axisLeftTrigger;
	public JoystickAxis axisRightTrigger;

	public Controller(int port) {

		// controller
		joystick = new Joystick(port);

		// buttons
		buttonA = new JoystickButton(joystick, 1);
		buttonB = new JoystickButton(joystick, 0);
		buttonX = new JoystickButton(joystick, 0);
		buttonY = new JoystickButton(joystick, 0);
		buttonLeftBumper = new JoystickButton(joystick, 0);
		buttonRightBumper = new JoystickButton(joystick, 0);

		// Axes
		axisLeftX = new JoystickAxis(joystick, ButtonMap.LeftXAxis, 0);
		axisLeftY = new JoystickAxis(joystick, ButtonMap.LeftYAxis, 0);
		axisRightX = new JoystickAxis(joystick, ButtonMap.RightXAxis, 0);
		axisRightY = new JoystickAxis(joystick, ButtonMap.RightYAxis, 0);
		axisLeftTrigger = new JoystickAxis(joystick, ButtonMap.LeftTrigger, 0);
		axisRightTrigger = new JoystickAxis(joystick, ButtonMap.RightTrigger, 0);
	}

	public void setRumble(double value) {
		// RUMBLE!!!!
		joystick.setRumble(RumbleType.kLeftRumble, value);
		joystick.setRumble(RumbleType.kRightRumble, value);

	}

	public class JoystickAxis {
		private Joystick joystick;
		private int axis;
		private double tolerance;

		public JoystickAxis(Joystick joystick, int axis, double tolerance) {
			this.axis = axis;
			this.joystick = joystick;
			this.tolerance = tolerance;

		}

		public double getAxisValue() {
			return Helpers.normalize(joystick.getRawAxis(axis), tolerance);
		}

	}
}
