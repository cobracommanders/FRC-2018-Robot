package team498.robot.dynamic;

/**
 * Under the hood class, should never be used by anyone other than the authors
 * @author Micah Neitz<br/>
 * 		   Team 498
 *
 */
interface ITask {
	void execute();
	void change(boolean value);
	void change(double value);
	boolean isButton();
}
