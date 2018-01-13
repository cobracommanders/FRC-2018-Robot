package team498.robot;
 
/**
 * 
 * @author Micah
 *
 */
public class ButtonMap {
  public static final int A = 1;
  public static final int B = 2;
  public static final int X = 3;
  public static final int Y = 4;
  public static final int LeftBumper = 5;
  public static final int RightBumper = 6;
  public static final int Back = 7;
  public static final int Start = 8;
  public static final int LeftJoyPress = 9;
  public static final int RightJoyPress = 10;
  
  public static final int LeftXAxis = 0;
  public static final int LeftYAxis = 1;
  public static final int RightXAxis = 6;
  public static final int RightYAxis = 5;
  public static final int RightTrigger = 3;
  public static final int LeftTrigger = 2;
  
  public int index;
 
  ButtonMap(int channel) {
    index = channel;
  }
}
 