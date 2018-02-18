package team498.robot.dynamic;

/**
 * This is the task interface, and should never be used. Under the hood class
 * 
 * @version 1.0
 * 
 * @author Micah Neitz <br/>
 *         Team 498
 */
interface ITask {
	abstract void Init();

	abstract void Run();

	abstract void Execute();

	abstract void Change(boolean value);

	abstract void Change(double value);

	abstract boolean IsButton();
}
