package team498.robot;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Controller {
	public Joystick joystick;

	public JoystickButton buttonA;
	public JoystickButton buttonB;
	public JoystickButton buttonX;
	public JoystickButton buttonY;
	public JoystickButton leftBumper;
	public JoystickButton rightBumper;
	public JoystickButton buttonLeftJoystick;
	public JoystickButton buttonRightJoystick;
	public JoystickButton buttonBack;
	public JoystickButton buttonStart;

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
		buttonA = new JoystickButton(joystick, Mappings.ButtonA);
		buttonB = new JoystickButton(joystick, Mappings.ButtonB);
		buttonX = new JoystickButton(joystick, Mappings.ButtonX);
		buttonY = new JoystickButton(joystick, Mappings.ButtonY);
		leftBumper = new JoystickButton(joystick, Mappings.LeftBumper);
		rightBumper = new JoystickButton(joystick, Mappings.RightBumper);
		buttonLeftJoystick = new JoystickButton(joystick, Mappings.LeftJoyPress);
		buttonRightJoystick = new JoystickButton(joystick, Mappings.RightJoyPress);
		buttonBack = new JoystickButton(joystick, Mappings.ButtonBack);
		buttonStart = new JoystickButton(joystick, Mappings.ButtonStart);

		// Axes
		axisLeftX = new JoystickAxis(joystick, Mappings.LeftXAxis, 0);
		axisLeftY = new JoystickAxis(joystick, Mappings.LeftYAxis, 0);
		axisRightX = new JoystickAxis(joystick, Mappings.RightXAxis, 0);
		axisRightY = new JoystickAxis(joystick, Mappings.RightYAxis, 0);
		axisLeftTrigger = new JoystickAxis(joystick, Mappings.LeftTrigger, 0);
		axisRightTrigger = new JoystickAxis(joystick, Mappings.RightTrigger, 0);
	}

	public void setRumble(double value) {
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

	public void updateDashboard() {
		SmartDashboard.putBoolean(Dashboard.IsButtonAPressed, buttonA.get());
		SmartDashboard.putBoolean(Dashboard.IsButtonBPressed, buttonB.get());
		SmartDashboard.putBoolean(Dashboard.IsButtonXPressed, buttonX.get());
		SmartDashboard.putBoolean(Dashboard.IsButtonYPressed, buttonY.get());
		SmartDashboard.putBoolean(Dashboard.IsLeftBumperPressed, leftBumper.get());
		SmartDashboard.putBoolean(Dashboard.IsRightBumperPressed, rightBumper.get());
		SmartDashboard.putNumber(Dashboard.LeftTriggerValue, axisLeftTrigger.getAxisValue());
		SmartDashboard.putNumber(Dashboard.RightTriggerValue, axisRightTrigger.getAxisValue());
		SmartDashboard.putNumber(Dashboard.AxisLeftXValue, axisLeftX.getAxisValue());
	}
}
