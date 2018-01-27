package team498.robot.dynamicAuto;

import java.util.*;

import team498.robot.Operator;

public class ButtonRecorder {

	Operator operator = Operator.getOperator();

	boolean a;
	boolean b;
	boolean x;
	boolean y;
	boolean rb;
	boolean lb;
	boolean ljp;
	boolean rjp;
	boolean back;
	boolean start;

	double lt;
	double rt;
	double ljx;
	double ljy;
	double rjx;
	double rjy;

	String changed = "";
	private final double flux = 0.05; // TODO: Change, this is axis fluctuations

	public List<String> detect() {
		
		List<String> tags = new ArrayList<String>();

		// Detects a change in button state
		if (a != operator.controller.buttonA.get())
			tags.add(buttonChange("a", !a));
		if (b != operator.controller.buttonB.get())
			tags.add(buttonChange("b", !b));
		if (x != operator.controller.buttonX.get())
			tags.add(buttonChange("x", !x));
		if (y != operator.controller.buttonY.get())
			tags.add(buttonChange("y", !y));
		if (lb != operator.controller.leftBumper.get())
			tags.add(buttonChange("lb", !lb));
		if (rb != operator.controller.rightBumper.get())
			tags.add(buttonChange("rb", !rb));
		if (ljp != operator.controller.buttonLeftJoystick.get())
			tags.add(buttonChange("ljp", !ljp));
		if (rjp != operator.controller.buttonRightJoystick.get())
			tags.add(buttonChange("rjp", !rjp));
		if (back != operator.controller.buttonBack.get())
			tags.add(buttonChange("back", !back));
		if (start != operator.controller.buttonStart.get())
			tags.add(buttonChange("start", !start));

		// Detects a change in axis state that is more than the axis flux
		if (Math.abs(lt - operator.controller.axisLeftTrigger.getAxisValue()) > flux)
			tags.add(buttonChange("lt", operator.controller.axisLeftTrigger.getAxisValue()));
		if (Math.abs(rt - operator.controller.axisRightTrigger.getAxisValue()) > flux)
			tags.add(buttonChange("rt", operator.controller.axisRightTrigger.getAxisValue()));
		if (Math.abs(ljx - operator.controller.axisLeftX.getAxisValue()) > flux)
			tags.add(buttonChange("ljx", operator.controller.axisLeftX.getAxisValue()));
		if (Math.abs(ljy - operator.controller.axisLeftY.getAxisValue()) > flux)
			tags.add(buttonChange("ljy", operator.controller.axisLeftY.getAxisValue()));
		if (Math.abs(rjx - operator.controller.axisRightX.getAxisValue()) > flux)
			tags.add(buttonChange("rjx", operator.controller.axisRightX.getAxisValue()));
		if (Math.abs(rjy - operator.controller.axisRightY.getAxisValue()) > flux)
			tags.add(buttonChange("rjy", operator.controller.axisRightY.getAxisValue()));

		System.out.println("Detecting " + tags.size());

		// Updates values
		_setValues();
		return tags;
	}

	private void _setValues() {
		a = operator.controller.buttonA.get();
		b = operator.controller.buttonB.get();
		x = operator.controller.buttonX.get();
		y = operator.controller.buttonY.get();
		lb = operator.controller.leftBumper.get();
		rb = operator.controller.rightBumper.get();
		ljp = operator.controller.buttonLeftJoystick.get();
		rjp = operator.controller.buttonRightJoystick.get();
		back = operator.controller.buttonBack.get();
		start = operator.controller.buttonStart.get();
		lt = operator.controller.axisLeftTrigger.getAxisValue();
		rt = operator.controller.axisRightTrigger.getAxisValue();
		ljx = operator.controller.axisLeftX.getAxisValue();
		ljy = operator.controller.axisLeftY.getAxisValue();
		rjx = operator.controller.axisRightX.getAxisValue();
		rjy = operator.controller.axisRightY.getAxisValue();
	}

	public void buttonChange(String changed) {
		this.changed = changed;
		System.out.println(changed);
	}

	public String buttonChange(String button, boolean value) {
		String tag = "";
		tag += button;
		tag += "^";
		tag += (value ? "1" : "0");
		return tag;
	}

	public String buttonChange(String button, double value) {
		String tag = "";
		tag += button;
		tag += "^";
		tag += value;
		return tag;
	}
}
