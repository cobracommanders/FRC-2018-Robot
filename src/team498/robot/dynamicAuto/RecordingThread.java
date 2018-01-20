package team498.robot.dynamicAuto;

import java.time.Clock;
import java.util.*;

import edu.wpi.first.wpilibj.Timer;
import team498.robot.Operator;
import team498.robot.dynamicAuto.*;

public class RecordingThread extends Thread {

	Operator operator = Operator.getOperator();

	//Buttons
	private final int a = 0;
	private final int b = 1;
	private final int x = 2;
	private final int y = 3;
	//Bumpers
	private final int lb = 4;
	private final int rb = 5;
	//Start and Back
	private final int back = 6;
	private final int start = 7;
	//Joystick Buttons
	private final int ljp = 8;
	private final int rjp = 9;
	//Triggers
	private final int lt = 0;
	private final int rt = 1;
	private final int ljx = 2;
	private final int ljy = 3;
	private final int rjx = 4;
	private final int rjy = 5;

	boolean[] buttons = new boolean[10];
	double[] axes = new double[6];
	String changed = "";
	private final double flux = 0.05; // TODO: Change, this is axis fluctuations

	List<ButtonListener> listeners = new ArrayList<ButtonListener>();

	@Override
	public void interrupt() {
		
	}
	
	@Override
	public void run() {
		_setValues();

		while (true) {
			
			//Detects a change in button state
			if (buttons[a] != operator.controller.buttonA.get())
				buttonChange("a" + (!buttons[a] ? "1" : "0"));
			if (buttons[b] != operator.controller.buttonB.get())
				buttonChange("b" + (!buttons[b] ? "1" : "0"));
			if (buttons[x] != operator.controller.buttonX.get())
				buttonChange("x" + (!buttons[x] ? "1" : "0"));
			if (buttons[y] != operator.controller.buttonY.get())
				buttonChange("y" + (!buttons[y] ? "1" : "0"));
			if (buttons[lb] != operator.controller.leftBumper.get())
				buttonChange("lb" + (!buttons[lb] ? "1" : "0"));
			if (buttons[rb] != operator.controller.rightBumper.get())
				buttonChange("rb" + (!buttons[rb] ? "1" : "0"));
			if (buttons[ljp] != operator.controller.buttonLeftJoystick.get())
				buttonChange("ljp" + (!buttons[ljp] ? "1" : "0"));
			if (buttons[rjp] != operator.controller.buttonRightJoystick.get())
				buttonChange("rjp" + (!buttons[rjp] ? "1" : "0"));
			if (buttons[back] != operator.controller.buttonBack.get())
				buttonChange("back" + (!buttons[back] ? "1" : "0"));
			if (buttons[start] != operator.controller.buttonStart.get())
				buttonChange("start" + (!buttons[start] ? "1" : "0"));
			
			//Detects a change in axis state that is more than the axis flux
			if (Math.abs(axes[lt] - operator.controller.axisLeftTrigger.getAxisValue()) > flux)
				buttonChange("lt" + (axes[lt] - operator.controller.axisLeftTrigger.getAxisValue() < 0 ? "+" : "-"));
			if (Math.abs(axes[rt] - operator.controller.axisRightTrigger.getAxisValue()) > flux)
				buttonChange("rt" + (axes[rt] - operator.controller.axisRightTrigger.getAxisValue() < 0 ? "+" : "-"));
			if (Math.abs(axes[ljx] - operator.controller.axisLeftX.getAxisValue()) > flux)
				buttonChange("ljx" + (axes[ljx] - operator.controller.axisLeftX.getAxisValue() < 0 ? "+" : "-"));
			if (Math.abs(axes[ljy] - operator.controller.axisLeftY.getAxisValue()) > flux)
				buttonChange("ljy" + (axes[ljy] - operator.controller.axisLeftY.getAxisValue() < 0 ? "+" : "-"));
			if (Math.abs(axes[rjx] - operator.controller.axisRightX.getAxisValue()) > flux)
				buttonChange("rjx" + (axes[rjx] - operator.controller.axisRightX.getAxisValue() < 0 ? "+" : "-"));
			if (Math.abs(axes[rjy] - operator.controller.axisRightY.getAxisValue()) > flux)
				buttonChange("rjy" + (axes[rjy] - operator.controller.axisRightY.getAxisValue() < 0 ? "+" : "-"));
			
			//Updates values
			_setValues();
		}
	}

	public void AddListener(ButtonListener bl) {
		listeners.add(bl);
	}

	private void _setValues() {
		buttons[a] = operator.controller.buttonA.get();
		buttons[b] = operator.controller.buttonB.get();
		buttons[x] = operator.controller.buttonX.get();
		buttons[y] = operator.controller.buttonY.get();
		buttons[lb] = operator.controller.leftBumper.get();
		buttons[rb] = operator.controller.rightBumper.get();
		buttons[ljp] = operator.controller.buttonLeftJoystick.get();
		buttons[rjp] = operator.controller.buttonRightJoystick.get();
		buttons[back] = operator.controller.buttonBack.get();
		buttons[start] = operator.controller.buttonStart.get();
		axes[lt] = operator.controller.axisLeftTrigger.getAxisValue();
		axes[rt] = operator.controller.axisRightTrigger.getAxisValue();
		axes[ljx] = operator.controller.axisLeftX.getAxisValue();
		axes[ljy] = operator.controller.axisLeftY.getAxisValue();
		axes[rjx] = operator.controller.axisRightX.getAxisValue();
		axes[rjy] = operator.controller.axisRightY.getAxisValue();
	}

	public void buttonChange(String changed) {
		this.changed = changed;
		for (ButtonListener bl : listeners)
			bl.buttonChange(changed);
	}
}
