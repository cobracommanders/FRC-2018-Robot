package team498.robot.dynamic;

/**
 * A task that doesn't have a value to be changed directly.<br/>
 * Inherit from this to add somehting as a passive in Recorder
 * 
 * @version 1.0
 * @author Micah Neitz<br/>
 *         Team 498
 *
 */
public abstract class PassiveTask extends Task {

	/**
	 * Isn't used in a passive task, so it's overriden and finalized so you don't
	 * have to worry about it
	 */
	@Override
	public final void Change(boolean value) {
	}

	/**
	 * Isn't used in a passive task, so it's overriden and finalized so you don't
	 * have to worry about it
	 */
	@Override
	public final void Change(double value) {
	}

	/**
	 * Isn't used in a passive task, so it's overriden and finalized so you don't
	 * have to worry about it
	 */
	@Override
	public final boolean IsButton() {
		return false;
	}
}
